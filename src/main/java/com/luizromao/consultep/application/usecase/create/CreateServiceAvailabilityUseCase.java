package com.luizromao.consultep.application.usecase.create;

import com.luizromao.consultep.application.dto.record.ServiceAvailabilityForm;
import com.luizromao.consultep.application.service.ServiceCepService;
import com.luizromao.consultep.domain.model.ServiceAvailability;
import com.luizromao.consultep.domain.repository.ServiceAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateServiceAvailabilityUseCase {

  private final ServiceAvailabilityRepository serviceAvailabilityRepository;
  private final ServiceCepService serviceCepService;

  public ServiceAvailability create(ServiceAvailabilityForm form) {
    var serviceCep = serviceCepService.getById(form.serviceCepId());

    ServiceAvailability serviceAvailability =
        ServiceAvailability.builder()
            .serviceCep(serviceCep)
            .cep(form.cep())
            .availabilityStatus(form.availabilityStatus())
            .build();

    return serviceAvailabilityRepository.save(serviceAvailability);
  }
}
