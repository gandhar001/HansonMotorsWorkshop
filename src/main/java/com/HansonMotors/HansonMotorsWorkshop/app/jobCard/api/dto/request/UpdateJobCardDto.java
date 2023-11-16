package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateJobCardDto {

  @NotEmpty(message = "repair types can't be empty")
  @NotNull
  private List<Long> repairTypeIds;
}
