package com.luizromao.consultep.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceCepDTO {

  private String id;
  private String serviceType;
  private String name;
  private String description;
  private CepApiMockDTO infoCep;
}
