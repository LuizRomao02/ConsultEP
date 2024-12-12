package com.luizromao.consultep.application.usecase.report;

import com.luizromao.consultep.domain.model.ServiceAvailability;
import com.luizromao.consultep.domain.repository.ServiceAvailabilityRepository;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

@Service
public class GenerateServiceAvailabilityReportUseCase {

  private final ServiceAvailabilityRepository serviceAvailabilityRepository;

  public GenerateServiceAvailabilityReportUseCase(
      ServiceAvailabilityRepository serviceAvailabilityRepository) {
    this.serviceAvailabilityRepository = serviceAvailabilityRepository;
  }

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
      throw new RuntimeException("Error writing CSV data", e);
    }
  }
}
