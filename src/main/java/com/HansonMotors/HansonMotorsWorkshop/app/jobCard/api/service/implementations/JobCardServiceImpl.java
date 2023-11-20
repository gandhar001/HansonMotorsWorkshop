package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.implementations;

import static com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.JobCardStatus.COMPLETED;
import static com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.JobCardStatus.NEW;
import static com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.implementations.RepairTypeServiceImpl.mapRepairTypeEntityToDto;

import com.HansonMotors.HansonMotorsWorkshop.app.exception.exceptionClasses.ResourceNotFoundException;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.JobCardDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.JobCardRepairTypeReqDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.UpdateJobCardDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.whatsapp.DocUploadRes;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.whatsapp.Document;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.whatsapp.WhatsappDocReqDTO;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.JobCardEstimateRepairTypeDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.JobCardEstimateResDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.JobCardRepairTypeDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.JobCardResponseDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.WhatsappResDTO;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.JobCard;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.JobCardRepairType;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.RepairType;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.JobCardStatus;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.feignProxies.WhatAppFeignClient;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.repository.JobCardRepository;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.repository.RepairTypeRepository;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions.JobCardService;
import jakarta.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
@RequiredArgsConstructor
public class JobCardServiceImpl implements JobCardService {

  private final JobCardRepository jobCardRepository;
  private final RepairTypeRepository repairTypeRepository;
  private final static ModelMapper modelMapper = new ModelMapper();
  private final PdfService pdfservice;
  private final WhatAppFeignClient whatAppFeignClient;
  @Value("${whatsapp.auth_token}")
  private String whatsAppAuthToken;
  private final static TypeMap<JobCardDto, JobCard> propertyMapper = modelMapper.createTypeMap(
      JobCardDto.class, JobCard.class);
  private final static TypeMap<JobCard, JobCardResponseDto> propertyMapperJobCardEntityToDto = modelMapper.createTypeMap(
      JobCard.class, JobCardResponseDto.class);
  private final static TypeMap<JobCard, JobCardEstimateResDto> propertyMapperJobCardEntityToEstimateDto = modelMapper.createTypeMap(
      JobCard.class, JobCardEstimateResDto.class);

  @Override
  @Transactional
  public Map<String, Object> addJobCard(JobCardDto jobCardDto) throws Exception {
    var addJobCardRes = new HashMap<String, Object>();

    propertyMapper.addMappings(mapper -> mapper.skip(JobCard::setRepairTypeAssoc));
    var jobCardEntity = modelMapper.map(jobCardDto, JobCard.class);

    // fetching repair types
    var addedRepairTypes = jobCardDto.getRepairTypes();

    mapJobCardRepairType(jobCardEntity, addedRepairTypes);

    jobCardEntity.setJobCardStatus(NEW);

    var savedJobCard = jobCardRepository.save(jobCardEntity);
    var jobCardEstimated = createJobCardEstimate(savedJobCard);
    var html = pdfservice.parseThymeleafTemplate(jobCardEstimated);
    pdfservice.generatePdfFromHtml(html);
    var docUploadRes = uploadJobCardToWhatsAppServer();
    var response = sendJobCardToWhatsApp(savedJobCard.getOwnerDetails().getPhone(),
        docUploadRes.getId());
    addJobCardRes.put("jobCardId", savedJobCard.getId());

    return addJobCardRes;

  }

//  private DocUploadRes sendWelcomeMessage() throws Exception {
//    String inputPath =
//        "C:\\Users\\gandh\\Desktop\\Hanson Motors\\pdf files" + File.separator + "jobCard.pdf";
//
//    FileInputStream input = new FileInputStream(inputPath);
//    MultipartFile multipartFile = new MockMultipartFile("file", "jobCard", "application/pdf",
//        IOUtils.toByteArray(input));
//    var result = whatAppFeignClient.uploadMedia(multipartFile, "application/pdf", "whatsapp",
//        whatsAppAuthToken);
//    var res = result.getBody();
//    return res;
//  }

  private DocUploadRes uploadJobCardToWhatsAppServer() throws Exception {
    String inputPath =
        "C:\\Users\\gandh\\Desktop\\Hanson Motors\\pdf files" + File.separator + "jobCard.pdf";

    FileInputStream input = new FileInputStream(inputPath);
    MultipartFile multipartFile = new MockMultipartFile("file", "jobCard", "application/pdf",
        IOUtils.toByteArray(input));
    var result = whatAppFeignClient.uploadMedia(multipartFile, "application/pdf", "whatsapp",
        whatsAppAuthToken);
    var res = result.getBody();
    return res;
  }

  private WhatsappResDTO sendJobCardToWhatsApp(String phoneNumber, String docId) throws Exception {

    var whatsAppDoc = WhatsappDocReqDTO.builder().type("document").messagingProduct("whatsapp")
        .recipientType("individual").to("91" + phoneNumber)
        .document(Document.builder().id(docId).caption("HansonMotorsEstimate").build()).build();
    var response = whatAppFeignClient.sendDocument(whatsAppDoc, whatsAppAuthToken);
    return response.getBody();
  }


  private JobCardEstimateResDto createJobCardEstimate(JobCard jobCard) {
    propertyMapperJobCardEntityToEstimateDto.addMappings(
        mapper -> mapper.skip(JobCardEstimateResDto::setRepairTypeAssoc));

    var jobCardEstimateResDto = modelMapper.map(jobCard, JobCardEstimateResDto.class);

    var repairTypeAssocResponse = jobCard.getRepairTypeAssoc().stream().map(
            jobCardRepairType -> JobCardEstimateRepairTypeDto.builder().amount(
                    Double.valueOf(jobCardRepairType.getRepairType().getCost()) * Double.valueOf(
                        jobCardRepairType.getQuantity()))
                .quantity(String.valueOf(jobCardRepairType.getQuantity()))
                .repairType(mapRepairTypeEntityToDto(jobCardRepairType.getRepairType())).build())
        .toList();

    jobCardEstimateResDto.setRepairTypeAssoc(repairTypeAssocResponse);
    var estimatedTotalAmount = jobCardEstimateResDto.getRepairTypeAssoc().stream()
        .map(el -> Double.valueOf(el.getRepairType().getCost())).reduce(0.0, Double::sum);

    jobCardEstimateResDto.setEstimatedServiceAmount(estimatedTotalAmount);

    return jobCardEstimateResDto;
  }

  private void mapJobCardRepairType(JobCard jobCardEntity,
      List<JobCardRepairTypeReqDto> addedRepairTypes) {
    if (null != addedRepairTypes && !addedRepairTypes.isEmpty()) {

      var repairTypeIds = addedRepairTypes.stream().map(JobCardRepairTypeReqDto::getId).toList();

      var repairTypes = repairTypeRepository.findAllById(repairTypeIds);

      if (repairTypes.isEmpty()) {
        throw new ResourceNotFoundException("Repair Type", "allRepairTypes",
            "Unable to fetch all repair types by ids");
      }
      List<JobCardRepairType> jobCardRepairTypes = new ArrayList<>();

      var repairTypeMap = repairTypes.stream().collect(Collectors.toMap(RepairType::getId, r -> r));

      addedRepairTypes.forEach(addedRepairType -> {
        var repairType = repairTypeMap.get(addedRepairType.getId());
        var jobCardRepairType = JobCardRepairType.builder().repairType(repairType)
            .quantity(addedRepairType.getQuantity()).jobCard(jobCardEntity).build();
        jobCardRepairTypes.add(jobCardRepairType);
      });

      jobCardEntity.setRepairTypeAssoc(jobCardRepairTypes);
    }
  }

  @Override
  @Transactional
  public Map<String, Object> fetchAllJobCards() throws ResourceNotFoundException {
    var allJobCardsRes = new HashMap<String, Object>();

    var response = fetchAllJobCardsGroupedByStatus();
    allJobCardsRes.put("jobCards", response);
    return allJobCardsRes;
  }

  @Transactional
  private Map<JobCardStatus, List<JobCardResponseDto>> fetchAllJobCardsGroupedByStatus() {

    var allJobCards = jobCardRepository.findJobCardsOrderByCreateDate();
    if (allJobCards.isEmpty()) {
      throw new ResourceNotFoundException("jobCard", "allJobCards",
          "Unable to fetch all available job cards");
    }
    propertyMapperJobCardEntityToDto.addMappings(
        mapper -> mapper.skip(JobCardResponseDto::setRepairTypeAssoc));

    var allJobCardsDto = allJobCards.stream().map(jobCard -> {
      var jobCardResponseDto = modelMapper.map(jobCard, JobCardResponseDto.class);
      var repairTypeAssocResponse = jobCard.getRepairTypeAssoc().stream().map(
              jobCardRepairType -> JobCardRepairTypeDto.builder()
                  .quantity(String.valueOf(jobCardRepairType.getQuantity()))
                  .repairType(mapRepairTypeEntityToDto(jobCardRepairType.getRepairType())).build())
          .toList();
      jobCardResponseDto.setRepairTypeAssoc(repairTypeAssocResponse);
      return jobCardResponseDto;
    }).collect(Collectors.groupingBy(JobCardResponseDto::getJobCardStatus));

    return allJobCardsDto;
  }

  @Override
  @Transactional
  public Map<String, Object> closeJobCard(UpdateJobCardDto updateJobCardDto, Long jobCardId)
      throws Exception {
    var closeJobCardResponse = new HashMap<String, Object>();

    var repairTypes = repairTypeRepository.findAllById(updateJobCardDto.getRepairTypeIds());

    var jobCardEntity = jobCardRepository.findById(jobCardId).orElseThrow(
        () -> new ResourceNotFoundException("jobCard", jobCardId.toString(),
            "Unable to fetch all available job cards"));

    // jobCardEntity.setRepairTypes(repairTypes);
    jobCardEntity.setJobCardStatus(COMPLETED);
    jobCardRepository.save(jobCardEntity);
    var response = fetchAllJobCardsGroupedByStatus();
    closeJobCardResponse.put("updatedJobCards", response);
    //var billData = billService.generateBill(jobCardId);

    closeJobCardResponse.put("jobCardId", jobCardId);
    log.info("job card saved successfully with id {}", jobCardId);
    return closeJobCardResponse;
  }

  @Override
  @Transactional
  public Map<String, Object> updateJobCardStatus(String jobCarStatus, Long JobCardId)
      throws Exception {
    var updateJobCardStatusResponse = new HashMap<String, Object>();

    var jobCardEntity = jobCardRepository.findById(JobCardId).orElseThrow(
        () -> new ResourceNotFoundException("jobCard", JobCardId.toString(),
            "Unable to fetch all available job cards"));

    jobCardEntity.setJobCardStatus(JobCardStatus.valueOf(jobCarStatus));
    jobCardRepository.save(jobCardEntity);

    var response = fetchAllJobCardsGroupedByStatus();
    updateJobCardStatusResponse.put("updatedJobCards", response);
    updateJobCardStatusResponse.put("jobCardId", JobCardId);
    log.info("job card updated successfully with id {}", JobCardId);
    return updateJobCardStatusResponse;
  }

  @Override
  @Transactional
  public Map<String, Object> fetchAllJobCardsByStatus(String jobCarStatus) throws Exception {
    var addJobCardRes = new HashMap<String, Object>();

    var jobCardsFetched = jobCardRepository.findByJobCardStatus(JobCardStatus.valueOf(jobCarStatus))
        .stream()

        .map(jobCard -> modelMapper.map(jobCard, JobCardResponseDto.class)).toList();

    addJobCardRes.put("jobCardsFetched", jobCardsFetched);
    log.info("job cards fetched successfully with status {}", jobCarStatus);
    return addJobCardRes;
  }

}

