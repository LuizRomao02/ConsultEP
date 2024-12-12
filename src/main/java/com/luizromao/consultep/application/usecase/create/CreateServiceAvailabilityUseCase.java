package com.luizromao.consultep.application.usecase.create;

import com.luizromao.consultep.application.dto.ServiceAvailabilityDTO;
import com.luizromao.consultep.application.dto.record.ServiceAvailabilityForm;
import com.luizromao.consultep.application.service.ServiceCepService;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.ServiceAvailability;
import com.luizromao.consultep.domain.repository.ServiceAvailabilityRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateServiceAvailabilityUseCase {

  private final ServiceAvailabilityRepository serviceAvailabilityRepository;
  private final ServiceCepService serviceCepService;
  private final ConverterToDTO converterToDTO;

  public CreateServiceAvailabilityUseCase(
      ServiceAvailabilityRepository serviceAvailabilityRepository,
      ServiceCepService serviceCepService,
      ConverterToDTO converterToDTO) {
    this.serviceAvailabilityRepository = serviceAvailabilityRepository;
    this.serviceCepService = serviceCepService;
    this.converterToDTO = converterToDTO;
  }

  public ServiceAvailabilityDTO create(ServiceAvailabilityForm form) {
    var serviceCep = serviceCepService.findServiceCepById(form.serviceCepId());
    ServiceAvailability serviceAvailability =
        ServiceAvailability.builder()
            .serviceCep(serviceCep)
            .cep(form.cep())
            .availabilityStatus(form.availabilityStatus())
            .build();

    return converterToDTO.toDto(serviceAvailabilityRepository.save(serviceAvailability));
  }
}
