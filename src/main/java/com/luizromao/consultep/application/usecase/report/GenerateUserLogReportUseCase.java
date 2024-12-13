package com.luizromao.consultep.application.usecase.report;

import com.luizromao.consultep.application.exception.ReportGenerationException;
import com.luizromao.consultep.domain.model.LogCep;
import com.luizromao.consultep.domain.repository.LogCepRepository;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GenerateUserLogReportUseCase {

  private final LogCepRepository logCepRepository;

  @Transactional(readOnly = true)
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
      throw new ReportGenerationException("Error generating user log CSV report", e);
    }
  }
}
