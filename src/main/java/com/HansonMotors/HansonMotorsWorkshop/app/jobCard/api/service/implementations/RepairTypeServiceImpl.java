package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.implementations;

import com.HansonMotors.HansonMotorsWorkshop.app.exception.exceptionClasses.ResourceNotFoundException;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.RepairTypeDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.RepairType;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.repository.RepairTypeRepository;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions.RepairTypeService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RepairTypeServiceImpl implements RepairTypeService {

  private final RepairTypeRepository repairTypeRepository;
  private final static ModelMapper modelMapper = new ModelMapper();

  private final static TypeMap<RepairTypeDto, RepairType> propertyMapperSetter = modelMapper.createTypeMap(
      RepairTypeDto.class, RepairType.class);

  private final static TypeMap<RepairType, RepairTypeDto> propertyMapperGetter = modelMapper.createTypeMap(
      RepairType.class, RepairTypeDto.class);


  @Override
  @Transactional
  public Map<String, Object> addRepairType(List<RepairTypeDto> repairTypeDtoList) {
    var addRepairTypeRes = new HashMap<String, Object>();
    var repairTypesResList = new ArrayList<>();

    propertyMapperSetter.addMappings(mapper -> mapper.skip(RepairType::setMetaData));
    repairTypeDtoList.forEach(repairTypeDto -> {
      var repairTypeEntity = modelMapper.map(repairTypeDto, RepairType.class);

      var metaDataFromDto = repairTypeDto.getMetaData();
      if (metaDataFromDto != null) {
        repairTypeEntity.setMetaData(metaDataFromDto.stream().collect(Collectors.joining(",")));
      }
      var savedRepairType = repairTypeRepository.save(repairTypeEntity);
      log.info("repair type saved successfully with id {}", savedRepairType.getId());
      var repairTypeDtoRes = mapRepairTypeEntityToDto(savedRepairType);
      repairTypesResList.add(repairTypeDtoRes);

    });
    addRepairTypeRes.put("savedRepairTypes", repairTypesResList);
    return addRepairTypeRes;
  }

  @Override
  @Transactional
  public Map<String, Object> fetchAllRepairTypes() throws ResourceNotFoundException {
    var allRepairTypesRes = new HashMap<String, Object>();
    propertyMapperGetter.addMappings(mapper -> mapper.skip(RepairTypeDto::setMetaData));

    var allRepairTypes = repairTypeRepository.findAll();
    if (allRepairTypes.isEmpty()) {
      throw new ResourceNotFoundException("repairTypes", "allRepairTypes",
          "Unable to fetch all available repair types");
    }
    var allRepairTypesDto = allRepairTypes.stream().map(el -> mapRepairTypeEntityToDto(el))
        .toList();
    allRepairTypesRes.put("repairTypes", allRepairTypesDto);
    return allRepairTypesRes;
  }

  public static RepairTypeDto mapRepairTypeEntityToDto(RepairType repairType) {

    var repairTypeDto = modelMapper.map(repairType, RepairTypeDto.class);
    var metaDataFromEntity = repairType.getMetaData();
    if (metaDataFromEntity != null) {
      var metaDataForDto = List.of(metaDataFromEntity.split(","));
      repairTypeDto.setMetaData(metaDataForDto);
    }
    return repairTypeDto;

  }


}
