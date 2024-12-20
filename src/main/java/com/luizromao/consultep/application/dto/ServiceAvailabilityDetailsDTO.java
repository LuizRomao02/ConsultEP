package com.luizromao.consultep.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAvailabilityDetailsDTO {

  private String id;
  private ServiceCepDTO serviceCep;
  private boolean availabilityStatus;
}
