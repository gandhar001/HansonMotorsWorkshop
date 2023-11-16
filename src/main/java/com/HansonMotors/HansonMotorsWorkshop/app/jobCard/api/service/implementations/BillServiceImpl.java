package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.implementations;

import com.HansonMotors.HansonMotorsWorkshop.app.exception.exceptionClasses.ResourceNotFoundException;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.Bill;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity.JobCard;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.BillStatus;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.PaidMode;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.repository.BillRepository;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.repository.JobCardRepository;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.service.defintions.BillService;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BillServiceImpl implements BillService {


  private final BillRepository billRepository;
  private final ModelMapper modelMapper;
  private final JobCardRepository jobCardRepository;

//@Override
//public Map<String, Object> addBillData(BillDto billDto) throws Exception {
//	var addBillRes = new HashMap<String, Object>();
//	//var billEntity = modelMapper.map(billDto, Bill.class);
//	Long jobCardId = billDto.getId();
//	if (jobCardId == null) {
//        addBillRes.put("message", "JobCardId is required in the BillDto.");
//        return addBillRes;
//    }
//	var billEntity = modelMapper.map(billDto, Bill.class);
//	 var generatedBill = generateBill(jobCardId);
//
//	 if (generatedBill != null) {
//	       
//	        addBillRes.put("bill", generatedBill);
//	        addBillRes.put("message", "Bill added successfully");
//	    } else {
//	        
//	        addBillRes.put("message", "Failed to generate the bill");
//	    }
//
//	    return addBillRes;
//	}


  @Override
  public Bill generateBill(Long jobCardId) throws ResourceNotFoundException {

    JobCard jobCard = jobCardRepository.findById(jobCardId).orElseThrow(
        () -> new ResourceNotFoundException("JobCard not found with ID: " + jobCardId));
    Bill bill = new Bill();
    bill.setCreatedByUser(jobCardId);
    bill.setJobCard(jobCard);
    bill.setTotalAmount(calculateTotalCostForJobCard(jobCard));
    bill.setBillStatus(BillStatus.UNPAID);
    bill.setPaidMode(PaidMode.CASH);
    Bill savedBill = billRepository.save(bill);

    return savedBill;


  }

  private double calculateTotalCostForJobCard(JobCard jobCard) throws ResourceNotFoundException {

//     double totalCost = jobCard.getRepairTypes().stream()
//             .mapToDouble(repairType -> repairType.getCost())
//             .sum();

    return 0.0;
  }

  @Override
  public Map<String, Object> fetchAllBills() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }


}
