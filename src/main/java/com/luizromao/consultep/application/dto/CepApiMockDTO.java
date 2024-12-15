package com.luizromao.consultep.application.dto;

import lombok.Data;

@Data
public class CepApiMockDTO {

  private String cep;
  private String logradouro;
  private String bairro;
  private String localidade;
  private String uf;
}
