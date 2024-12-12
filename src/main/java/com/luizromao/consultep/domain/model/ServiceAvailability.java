package com.luizromao.consultep.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAvailability extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "service_cep_id", nullable = false)
  private ServiceCep serviceCep;

  @Column(nullable = false, length = 8)
  private String cep;

  @Column(name = "availability_status", nullable = false)
  private boolean availabilityStatus;
}
