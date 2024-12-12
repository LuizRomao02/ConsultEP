package com.luizromao.consultep.application.usecase.report;

import com.luizromao.consultep.domain.model.LogCep;
import com.luizromao.consultep.domain.repository.LogCepRepository;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

@Service
public class GenerateUserLogReportUseCase {

  private final LogCepRepository logCepRepository;

  public GenerateUserLogReportUseCase(LogCepRepository logCepRepository) {
    this.logCepRepository = logCepRepository;
  }

  public void execute(Writer writer) {
    List<LogCep> logs = logCepRepository.findAll();

    try (CSVPrinter csvPrinter =
        new CSVPrinter(
            writer, CSVFormat.DEFAULT.withHeader("Log ID,User ID,CEP,Request Type,Request Time"))) {
      for (LogCep log : logs) {
        csvPrinter.printRecord(
            log.getId(),
            log.getUserCep().getId(),
            log.getCep(),
            log.getRequestType(),
            log.getCreatedAt());
      }
    } catch (IOException e) {
      throw new RuntimeException("Error writing CSV data", e);
    }
  }
}
