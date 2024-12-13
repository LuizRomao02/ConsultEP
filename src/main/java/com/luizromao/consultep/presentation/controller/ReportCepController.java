package com.luizromao.consultep.presentation.controller;

import com.luizromao.consultep.application.usecase.report.GenerateServiceAvailabilityReportUseCase;
import com.luizromao.consultep.application.usecase.report.GenerateUserLogReportUseCase;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report_cep")
@RequiredArgsConstructor
public class ReportCepController {

  private final GenerateUserLogReportUseCase generateUserLogReportUseCase;
  private final GenerateServiceAvailabilityReportUseCase generateServiceAvailabilityReportUseCase;

  @GetMapping(value = "/generate_user_log_report", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAllGenerateUserLogReport(HttpServletResponse response)
      throws IOException {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=log_report.csv");

    generateUserLogReportUseCase.execute(response.getWriter());

    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/generate_availability_report", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAllGenerateAvailabilityReport(HttpServletResponse response)
      throws IOException {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=availability_report.csv");

    generateServiceAvailabilityReportUseCase.execute(response.getWriter());

    return ResponseEntity.ok().build();
  }
}
