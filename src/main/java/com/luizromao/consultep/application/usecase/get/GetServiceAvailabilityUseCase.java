package com.luizromao.consultep.application.usecase.get;

import com.luizromao.consultep.application.exception.ServiceAvailabilityException;
import com.luizromao.consultep.domain.model.ServiceAvailability;
import com.luizromao.consultep.domain.repository.ServiceAvailabilityRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetServiceAvailabilityUseCase {

  private final ServiceAvailabilityRepository serviceAvailabilityRepository;

  @Transactional(readOnly = true)
  public boolean checkServiceAvailability(String serviceCepId, String cep) {
    Optional<ServiceAvailability> availability =
        serviceAvailabilityRepository.findByServiceCepIdAndCep(serviceCepId, cep);
    return availability.map(ServiceAvailability::isAvailabilityStatus).orElse(false);
  }

  @Transactional(readOnly = true)
  public Page<ServiceAvailability> findAllServiceAvailability(Pageable pageable) {
    return serviceAvailabilityRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public ServiceAvailability getOneServiceAvailability(String id) {
    return serviceAvailabilityRepository
        .findById(id)
        .orElseThrow(
            () ->
                new ServiceAvailabilityException(
                    HttpStatus.NOT_FOUND, "Service Availability not found"));
  }

  @Transactional(readOnly = true)
  public Page<ServiceAvailability> getByCep(String cep, Pageable pageable) {
    Page<ServiceAvailability> page = serviceAvailabilityRepository.findByCep(cep, pageable);

    return page.map(r -> r != null ? r : new ServiceAvailability());
  }
}