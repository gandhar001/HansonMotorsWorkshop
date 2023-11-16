package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.controller;

import com.HansonMotors.HansonMotorsWorkshop.app.commons.response.ResponseDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.JobCardDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.RepairTypeDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions.JobCardService;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions.RepairTypeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ("${repair-type.api.path}"))
@AllArgsConstructor
public class RepairTypeController {

  private final RepairTypeService repairTypeService;

  @PostMapping(value = "/add")
  public ResponseEntity<ResponseDto> addRepairTypes(
      @RequestBody List<RepairTypeDto> repairTypeDtoList) throws Exception {

    return new ResponseEntity<>(new ResponseDto(repairTypeService.addRepairType(repairTypeDtoList)),
        HttpStatus.OK);
  }

  @GetMapping(value = "/fetch-all")
  public ResponseEntity<ResponseDto> fetchAll() throws Exception {

    return new ResponseEntity<>(new ResponseDto(repairTypeService.fetchAllRepairTypes()),
        HttpStatus.OK);

  }
}
