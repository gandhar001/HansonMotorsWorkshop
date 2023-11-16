package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.RepairTypeDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.RepairType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class JobCardEstimateRepairTypeDto {

  private RepairTypeDto repairType;
  private String quantity;
  private Double amount;
}
