package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.repository;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.JobCard;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.RepairType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairTypeRepository extends JpaRepository<RepairType, Long> {

}
