package com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.entity;


import com.HansonMotors.HansonMotorsWorkshop.app.jobCard.api.enums.JobCardStatus;
import com.HansonMotors.HansonMotorsWorkshop.app.user.api.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.sql.Driver;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class JobCard implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private LocalDateTime dateOfService;

  @OneToOne(targetEntity = OwnerDetails.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "ownerId")
  private OwnerDetails ownerDetails;

  @Column(nullable = false)
  private Long createdByUser;

  @OneToOne(targetEntity = VehicleDetails.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "vehicleId")
  private VehicleDetails vehicleDetails;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "jobCard")
  private List<JobCardRepairType> repairTypeAssoc;

  @Enumerated(EnumType.STRING)
  private JobCardStatus jobCardStatus;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private LocalDateTime updatedAt;


}
