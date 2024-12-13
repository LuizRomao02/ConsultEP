package com.luizromao.consultep.application.usecase.report;

import com.luizromao.consultep.application.exception.ReportGenerationException;
import com.luizromao.consultep.domain.model.ServiceAvailability;
import com.luizromao.consultep.domain.repository.ServiceAvailabilityRepository;
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
public class GenerateServiceAvailabilityReportUseCase {

  private final ServiceAvailabilityRepository serviceAvailabilityRepository;

  @Transactional(readOnly = true)
  public void execute(Writer writer) {
    List<ServiceAvailability> availabilityList = serviceAvailabilityRepository.findAll();

    try (CSVPrinter csvPrinter =
        new CSVPrinter(
            writer, CSVFormat.DEFAULT.withHeader("Service ID", "CEP", "Availability Status"))) {
      for (ServiceAvailability availability : availabilityList) {
        csvPrinter.printRecord(
            availability.getServiceCep().getId(),
            availability.getCep(),
            availability.isAvailabilityStatus());
      }
    } catch (IOException e) {
      throw new ReportGenerationException("Error generating service availability CSV report", e);
    }
  }
}
