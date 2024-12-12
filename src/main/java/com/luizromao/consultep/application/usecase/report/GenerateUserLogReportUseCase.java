package com.luizromao.consultep.application.usecase.report;

import com.luizromao.consultep.domain.model.LogCep;
import com.luizromao.consultep.domain.repository.LogCepRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GenerateUserLogReportUseCase {

  private final LogCepRepository logCepRepository;

  public GenerateUserLogReportUseCase(LogCepRepository logCepRepository) {
    this.logCepRepository = logCepRepository;
  }

  public String execute() {
    List<LogCep> logs = logCepRepository.findAll();

    StringBuilder report = new StringBuilder();
    report.append("Log ID,User ID,CEP,Request Type,Request Time\n");

    for (LogCep log : logs) {
      report
          .append(log.getId())
          .append(",")
          .append(log.getUserCep().getId())
          .append(",")
          .append(log.getCep())
          .append(",")
          .append(log.getRequestType())
          .append(",")
          .append(log.getCreatedAt())
          .append("\n");
    }

    return report.toString();
  }
}
