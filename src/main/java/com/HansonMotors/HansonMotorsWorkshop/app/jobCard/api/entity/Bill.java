package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity;

import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.BillStatus;
import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.PaidMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor


public class Bill implements Serializable  {
	 public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JobCard getJobCard() {
		return jobCard;
	}

	public void setJobCard(JobCard jobCard) {
		this.jobCard = jobCard;
	}

	public Long getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Long createdByUser) {
		this.createdByUser = createdByUser;
	}

	public BillStatus getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(BillStatus billStatus) {
		this.billStatus = billStatus;
	}

	public PaidMode getPaidMode() {
		return paidMode;
	}

	public void setPaidMode(PaidMode paidMode) {
		this.paidMode = paidMode;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Bill [  createdByUser=" + createdByUser + ", billStatus="
				+ billStatus + ", paidMode=" + paidMode + ", totalAmount=" + totalAmount + "]";
	}

	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long id;
	

	@OneToOne(targetEntity = JobCard.class, cascade = CascadeType.ALL)
	 @JoinColumn(name = "job_card_id")
	    private JobCard jobCard;
	 
	 @Column(nullable = false)
	  private Long createdByUser;
	 
	 @Enumerated(EnumType.STRING)
	  private BillStatus billStatus;
	 
	 @Enumerated(EnumType.STRING)
	 private PaidMode paidMode;
	 
	 @Column(nullable = false)
	    private double totalAmount;
	 
	


}
