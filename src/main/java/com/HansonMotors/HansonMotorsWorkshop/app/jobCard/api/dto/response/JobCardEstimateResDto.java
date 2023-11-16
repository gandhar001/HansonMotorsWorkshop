package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.OwnerDetailsDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.VehicleDetailsDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.JobCardStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobCardEstimateResDto {

  private OwnerDetailsDto ownerDetails;
  private VehicleDetailsDto vehicleDetails;
  private JobCardStatus jobCardStatus;
  private long createdByUser;
  private long id;
  private LocalDateTime dateOfService;
  private List<JobCardEstimateRepairTypeDto> repairTypeAssoc;
  private double estimatedServiceAmount;
}
