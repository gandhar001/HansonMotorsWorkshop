package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.RepairTypeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class JobCardRepairTypeDto {

  private RepairTypeDto repairType;
  private String quantity;
}
