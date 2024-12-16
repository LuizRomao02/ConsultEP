package com.luizromao.consultep.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckServiceAvailabilityDTO {

  private String id;
  private boolean availabilityStatus;
}
