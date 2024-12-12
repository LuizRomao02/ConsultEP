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
public class LogCep extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "user_cep_id", nullable = false)
  private UserCep userCep;

  @Column(nullable = false, length = 8)
  private String cep;

  @Column(name = "request_type", nullable = false, length = 50)
  private String requestType;
}
