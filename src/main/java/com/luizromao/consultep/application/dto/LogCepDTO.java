package com.luizromao.consultep.application.dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogCepDTO {

  private String id;
  private String userCepId;
  private String cep;
  private String requestType;
  private String jsonData;
  private LocalDateTime createdAt;
}
