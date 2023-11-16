package com.HansonMotors.HansonMotorsWorkshop.app.user.api.service.implementations;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.JobCardDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions.JobCardService;
import com.HansonMotors.HansonMotorsWorkshop.app.user.api.service.defintions.UserService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public Map<String, Object> addJobCard(JobCardDto jobCardDto) throws Exception {
    return null;
  }
}
