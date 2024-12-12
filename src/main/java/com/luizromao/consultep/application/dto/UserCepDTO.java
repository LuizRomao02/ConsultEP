package com.luizromao.consultep.application.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCepDTO {

  private String id;
  private String name;
  private String email;
  private List<LogCepDTO> logs;
}
