package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.JobCardDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.UpdateJobCardDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.JobCardResponseDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.JobCardStatus;
import java.util.List;
import java.util.Map;


public interface JobCardService {

  Map<String, Object> addJobCard(JobCardDto jobCardDto) throws Exception;

  Map<String, Object> fetchAllJobCards() throws Exception;

  Map<String, Object> closeJobCard(UpdateJobCardDto updateJobCardDto,
      Long JobCardId) throws Exception;

  Map<String, Object> updateJobCardStatus(String jobCarStatus, Long JobCardId) throws Exception;

  Map<String, Object> fetchAllJobCardsByStatus(String jobCarStatus) throws Exception;


}
