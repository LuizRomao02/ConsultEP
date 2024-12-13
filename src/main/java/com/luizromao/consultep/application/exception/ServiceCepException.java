package com.luizromao.consultep.application.exception;

import java.io.Serial;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceCepException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  private final HttpStatus httpStatus;

  public ServiceCepException(HttpStatus httpStatus, final String message) {
    super(message);
    this.httpStatus = httpStatus;
  }
}