package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.controller;

import com.HansonMotors.HansonMotorsWorkshop.app.commons.response.ResponseDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.JobCardDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.UpdateJobCardDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions.JobCardService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://192.168.1.4:8081", maxAge = 3600)
@RestController
@RequestMapping(value = ("${job-card.api.path}"))
@RequiredArgsConstructor
public class JobCardController {

  private final JobCardService jobCardService;

  @PostMapping(value = "/add")
  public ResponseEntity<ResponseDto> addJobCard(@RequestBody JobCardDto jobCardDto)
      throws Exception {

    var response = jobCardService.addJobCard(jobCardDto);
    return new ResponseEntity<>(new ResponseDto(response), HttpStatus.OK);
  }

  @GetMapping(value = "/fetch-all")
  public ResponseEntity<ResponseDto> fetchAllJobCardsByStatus() throws Exception {

    return new ResponseEntity<>(new ResponseDto(jobCardService.fetchAllJobCards()),
        HttpStatus.OK);

  }

  @GetMapping(value = "/fetch-all/{jobCardStatus}")
  public ResponseEntity<ResponseDto> fetchAllJobCards(@PathVariable String jobCardStatus)
      throws Exception {

    return new ResponseEntity<>(
        new ResponseDto(jobCardService.fetchAllJobCardsByStatus(jobCardStatus)), HttpStatus.OK);

  }


  @PatchMapping(value = "/{jobCardId}/update-status/{newStatus}")
  public ResponseEntity<ResponseDto> updateJobCardStatus(@PathVariable String newStatus,
      @PathVariable String jobCardId) throws Exception {

    return new ResponseEntity<>(
        new ResponseDto(jobCardService.updateJobCardStatus(newStatus, Long.valueOf(jobCardId))),
        HttpStatus.OK);

  }

  @PatchMapping(value = "/{jobCardId}/update")
  public ResponseEntity<ResponseDto> updateJobCard(
      @Valid @RequestBody UpdateJobCardDto updateJobCardDto,
      @NotNull @PathVariable String jobCardId)
      throws Exception {
    return new ResponseEntity<>(
        new ResponseDto(jobCardService.closeJobCard(updateJobCardDto, Long.valueOf(jobCardId))),
        HttpStatus.OK);

  }

}
