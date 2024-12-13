package com.luizromao.consultep.application.exception;

import java.io.Serial;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserCepException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  private final HttpStatus httpStatus;

  public UserCepException(HttpStatus httpStatus, final String message) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
