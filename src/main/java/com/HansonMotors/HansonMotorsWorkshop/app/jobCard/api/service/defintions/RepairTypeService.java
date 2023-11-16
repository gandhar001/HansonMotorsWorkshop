package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.JobCardDto;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.request.RepairTypeDto;
import java.util.List;
import java.util.Map;

public interface RepairTypeService {

  Map<String, Object> addRepairType(List<RepairTypeDto> repairTypeDtoList) throws Exception;

  Map<String, Object> fetchAllRepairTypes() throws Exception;
//  Map<String, Object> fetchById(Long id) throws Exception;
}
