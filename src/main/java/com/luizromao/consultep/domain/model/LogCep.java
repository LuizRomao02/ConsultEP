package com.luizromao.consultep.domain.model;

import jakarta.persistence.*;
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
  @JoinColumn(name = "user_cep_id")
  private UserCep userCep;

  @Column(length = 8)
  private String cep;

  @Column(name = "request_type", nullable = false, length = 50)
  private String requestType;

  @Lob
  @Column(columnDefinition = "LONGTEXT")
  private String jsonData;
}
