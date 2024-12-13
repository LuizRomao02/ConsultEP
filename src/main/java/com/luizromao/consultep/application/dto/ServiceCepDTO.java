package com.luizromao.consultep.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCepDTO {

  private String id;
  private String serviceType;
  private String name;
  private String description;
}
