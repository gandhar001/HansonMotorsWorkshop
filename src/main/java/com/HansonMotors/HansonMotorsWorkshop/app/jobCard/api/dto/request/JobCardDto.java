package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request;

import com.HansonMotors.HansonMotorsWorkshop.app.user.api.dto.request.UserDto;
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
public class JobCardDto {

  private long id;

  private LocalDateTime dateOfService;

  private OwnerDetailsDto ownerDetails;

  private long createdByUser;

  private List<JobCardRepairTypeReqDto> repairTypes;

  private VehicleDetailsDto vehicleDetails;

}

