package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@IdClass(JobCardRepairType.class)
public class JobCardRepairType {

  @Id
  @ManyToOne
  @JoinColumn(name = "job_card_id", referencedColumnName = "id")
  private JobCard jobCard;

  @Id
  @ManyToOne
  @JoinColumn(name = "repair_type_id", referencedColumnName = "id")
  private RepairType repairType;


  private double quantity;
}
