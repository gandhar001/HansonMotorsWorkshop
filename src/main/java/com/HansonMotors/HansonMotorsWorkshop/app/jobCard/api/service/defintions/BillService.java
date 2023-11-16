package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions;

import com.HansonMotors.HansonMotorsWorkshop.app.exception.exceptionClasses.ResourceNotFoundException;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.Bill;
import java.util.Map;

public interface BillService {
	//Map<String, Object> addBillData(BillDto billDto) throws Exception;
	Map<String, Object> fetchAllBills() throws Exception;
	
	Bill generateBill(Long jobCardId) throws ResourceNotFoundException;

	

//	Map<String, Object> updateBill(Long JobCardId,PaidMode paidMode, BillStatus billStatus);

	

	//Map<String, Object> addBill(BillDto billDto);
	
}
