package com.HansonMotors.HansonMotorsWorkshop.app.user.api.service.defintions;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.JobCardDto;
import java.util.Map;

public interface UserService {

  public Map<String, Object> addJobCard(JobCardDto jobCardDto) throws Exception;


}
