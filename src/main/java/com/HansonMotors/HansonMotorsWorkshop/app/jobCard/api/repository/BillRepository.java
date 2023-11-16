package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.repository;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	
	 
}
