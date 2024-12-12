package com.luizromao.consultep.application.usecase.report;

import com.luizromao.consultep.domain.model.ServiceAvailability;
import com.luizromao.consultep.domain.repository.ServiceAvailabilityRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GenerateServiceAvailabilityReportUseCase {

  private final ServiceAvailabilityRepository serviceAvailabilityRepository;

  public GenerateServiceAvailabilityReportUseCase(
      ServiceAvailabilityRepository serviceAvailabilityRepository) {
    this.serviceAvailabilityRepository = serviceAvailabilityRepository;
  }

  public String execute() {
    List<ServiceAvailability> availabilityList = serviceAvailabilityRepository.findAll();

    StringBuilder report = new StringBuilder();
    report.append("Service ID,CEP,Availability Status\n");

    for (ServiceAvailability availability : availabilityList) {
      report
          .append(availability.getServiceCep().getId())
          .append(",")
          .append(availability.getCep())
          .append(",")
          .append(availability.isAvailabilityStatus())
          .append("\n");
    }

    return report.toString();
  }
}
