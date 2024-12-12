package com.luizromao.consultep.application.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogCepDTO {

  private String id;
  private String cep;
  private String requestType;
  private LocalDateTime createdAt;
}
