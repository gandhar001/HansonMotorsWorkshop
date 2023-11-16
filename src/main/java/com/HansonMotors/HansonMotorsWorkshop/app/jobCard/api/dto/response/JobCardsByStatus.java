package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.JobCard;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.JobCardStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobCardsByStatus {

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private List<JobCard> jobCards;

  private JobCardStatus jobCardStatus;

}
