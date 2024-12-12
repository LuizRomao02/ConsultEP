package com.luizromao.consultep.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAvailabilityDTO {

  private String id;
  private String cep;
  private ServiceCepDTO serviceCep;
  private boolean availabilityStatus;
}
