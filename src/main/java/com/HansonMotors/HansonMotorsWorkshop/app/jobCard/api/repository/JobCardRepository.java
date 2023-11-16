package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.repository;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.JobCardsByStatus;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.JobCard;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.JobCardStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCardRepository extends JpaRepository<JobCard, Long> {

  List<JobCard> findByJobCardStatus(JobCardStatus jobCardStatus);

  @Query(
      "SELECT new com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.dto.response.JobCardsByStatus(j,j.jobCardStatus) "
          + "FROM JobCard j GROUP BY j.jobCardStatus ORDER BY j.createdAt DESC")
  JobCardsByStatus findAllJobCardsGroupByStatus();


  @Query(
      "SELECT j FROM JobCard j ORDER BY j.createdAt DESC")
  List<JobCard> findJobCardsOrderByCreateDate();

}
