package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDetailsDto {

  private long id;
  private String vehicleNumber;
  private String model;
  private String chasisNumber;
  private String engineNumber;
  private String paint;
  private String kilometers;

}
