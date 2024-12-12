package com.luizromao.consultep.application.usecase.get;

import com.luizromao.consultep.domain.model.ServiceAvailability;
import com.luizromao.consultep.domain.repository.ServiceAvailabilityRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetServiceAvailabilityUseCase {

  private final ServiceAvailabilityRepository serviceAvailabilityRepository;

  public GetServiceAvailabilityUseCase(
      ServiceAvailabilityRepository serviceAvailabilityRepository) {
    this.serviceAvailabilityRepository = serviceAvailabilityRepository;
  }

  public boolean checkServiceAvailability(String serviceCepId, String cep) {
    Optional<ServiceAvailability> availability =
        serviceAvailabilityRepository.findByServiceCepIdAndCep(serviceCepId, cep);
    return availability.map(ServiceAvailability::isAvailabilityStatus).orElse(false);
  }

  public Page<ServiceAvailability> findAllServiceAvailability(Pageable pageable) {
    return serviceAvailabilityRepository.findAll(pageable);
  }
}
