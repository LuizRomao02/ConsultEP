package com.luizromao.consultep.application.exception;

import java.io.Serial;

public class ReportGenerationException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public ReportGenerationException(String message, Throwable cause) {
    super(message, cause);
  }
}
