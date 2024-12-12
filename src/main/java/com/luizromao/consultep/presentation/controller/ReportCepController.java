package com.luizromao.consultep.presentation.controller;

import com.luizromao.consultep.application.usecase.report.GenerateServiceAvailabilityReportUseCase;
import com.luizromao.consultep.application.usecase.report.GenerateUserLogReportUseCase;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
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

  @GetMapping("/generate_user_log_report")
  public ResponseEntity<?> getAllGenerateUserLogReport(HttpServletResponse response) {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=log_report.csv");

    try {
      generateUserLogReportUseCase.execute(response.getWriter());
    } catch (IOException e) {
      return ResponseEntity.internalServerError().body("Error generating CSV file");
    }

    return ResponseEntity.ok().build();
  }

  @GetMapping("/generate_availability_report")
  public ResponseEntity<?> getAllGenerateAvailabilityReport(HttpServletResponse response) {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=availability_report.csv");

    try {
      generateServiceAvailabilityReportUseCase.execute(response.getWriter());
    } catch (IOException e) {
      return ResponseEntity.internalServerError().body("Error generating CSV file");
    }

    return ResponseEntity.ok().build();
  }
}
