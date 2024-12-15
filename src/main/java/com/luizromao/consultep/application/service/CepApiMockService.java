package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.CepApiMockDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CepApiMockService {

  @Value("${spring.api_mock.url}")
  private String urlApi;

  private final WebClient webClient;

  public CepApiMockService(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl(urlApi).build();
  }

  public CepApiMockDTO fetchCepApiMock(String cep) {
    String fullUrl = String.format("%s%s", urlApi, cep);

    return webClient.get().uri(fullUrl).retrieve().bodyToMono(CepApiMockDTO.class).block();
  }
}
