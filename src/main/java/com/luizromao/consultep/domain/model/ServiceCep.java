package com.luizromao.consultep.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
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
public class ServiceCep extends BaseEntity {

  @Column(name = "service_type", nullable = false, length = 50)
  private String serviceType;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @OneToMany(mappedBy = "serviceCep", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ServiceAvailability> serviceAvailabilities;
}
