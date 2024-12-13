package com.luizromao.consultep.application.usecase.delete;

import com.luizromao.consultep.application.usecase.get.GetServiceAvailabilityUseCase;
import com.luizromao.consultep.domain.repository.ServiceAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteServiceAvailabilityUseCase {

  private final ServiceAvailabilityRepository serviceAvailabilityRepository;
  private final GetServiceAvailabilityUseCase getServiceAvailability;

  @Transactional
  public void deleteServiceAvailability(String id) {
    var serviceAvailability = getServiceAvailability.getOneServiceAvailability(id);
    serviceAvailabilityRepository.delete(serviceAvailability);
  }
}
