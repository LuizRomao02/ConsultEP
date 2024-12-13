package com.luizromao.consultep.application.usecase.update;

import com.luizromao.consultep.application.dto.record.ServiceAvailabilityForm;
import com.luizromao.consultep.application.usecase.get.GetServiceAvailabilityUseCase;
import com.luizromao.consultep.domain.model.ServiceAvailability;
import com.luizromao.consultep.domain.repository.ServiceAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateServiceAvailabilityUseCase {

  private final ServiceAvailabilityRepository serviceAvailabilityRepository;
  private final GetServiceAvailabilityUseCase getServiceAvailability;

  @Transactional
  public ServiceAvailability execute(String id, ServiceAvailabilityForm form) {
    var serviceAvailability = getServiceAvailability.getOneServiceAvailability(id);

    serviceAvailability.setCep(form.cep());
    serviceAvailability.setAvailabilityStatus(form.availabilityStatus());

    return serviceAvailabilityRepository.save(serviceAvailability);
  }
}
