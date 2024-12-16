package com.luizromao.consultep.application.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonConverter {

  private final ObjectMapper objectMapper;

  public String toJson(Object object) {
    try {

      objectMapper
          .findAndRegisterModules()
          .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      return objectMapper.writeValueAsString(object);

    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error converting object to JSON", e);
    }
  }
}
