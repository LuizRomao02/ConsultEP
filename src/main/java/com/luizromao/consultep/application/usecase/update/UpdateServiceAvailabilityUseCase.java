package com.luizromao.consultep.application.usecase.update;

import com.luizromao.consultep.application.dto.ServiceAvailabilityDTO;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.ServiceAvailability;
import com.luizromao.consultep.domain.model.ServiceCep;
import com.luizromao.consultep.domain.repository.ServiceAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateServiceAvailabilityUseCase {

  private final ServiceAvailabilityRepository serviceAvailabilityRepository;
  private final ConverterToDTO converterToDTO;

  @Transactional
  public ServiceAvailabilityDTO execute(
      String serviceCepId, String cep, boolean availabilityStatus) {
    ServiceAvailability serviceAvailability =
        serviceAvailabilityRepository
            .findByServiceCepIdAndCep(serviceCepId, cep)
            .orElseGet(
                () ->
                    ServiceAvailability.builder()
                        .serviceCep(ServiceCep.builder().build())
                        .cep(cep)
                        .build());

    serviceAvailability.setAvailabilityStatus(availabilityStatus);

    return converterToDTO.toDto(serviceAvailabilityRepository.save(serviceAvailability));
  }
}
