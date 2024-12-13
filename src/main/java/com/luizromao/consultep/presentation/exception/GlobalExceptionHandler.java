package com.luizromao.consultep.presentation.exception;

import com.luizromao.consultep.application.exception.ReportGenerationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ReportGenerationException.class)
  public ResponseEntity<String> handleReportGenerationException(ReportGenerationException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Failed to generate report: " + ex.getMessage());
  }
}
