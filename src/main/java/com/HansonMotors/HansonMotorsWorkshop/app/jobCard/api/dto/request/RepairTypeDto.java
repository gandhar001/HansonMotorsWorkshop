package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepairTypeDto {

  private long id;
  @NotNull
  private String repairType;
  private String cost;
  private List<String> metaData;
}
