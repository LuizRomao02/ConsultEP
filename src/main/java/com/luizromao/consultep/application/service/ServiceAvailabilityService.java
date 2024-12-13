package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.ServiceAvailabilityDTO;
import com.luizromao.consultep.application.dto.record.ServiceAvailabilityForm;
import com.luizromao.consultep.application.usecase.create.CreateServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.delete.DeleteServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.get.GetServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.update.UpdateServiceAvailabilityUseCase;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.application.validation.ValidCep;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceAvailabilityService {

  private final CreateServiceAvailabilityUseCase createServiceAvailability;
  private final DeleteServiceAvailabilityUseCase deleteServiceAvailability;
  private final UpdateServiceAvailabilityUseCase updateServiceAvailability;
  private final GetServiceAvailabilityUseCase getServiceAvailability;
  private final ConverterToDTO converterToDTO;
  private final LogCepService logCepService;

  public ServiceAvailabilityDTO create(ServiceAvailabilityForm form) {
    return converterToDTO.toDto(createServiceAvailability.create(form));
  }

  public Page<ServiceAvailabilityDTO> findAllServiceAvailability(Pageable pageable) {
    return getServiceAvailability.findAllServiceAvailability(pageable).map(converterToDTO::toDto);
  }

  public Boolean checkAvailability(String serviceId, @ValidCep String cep, String userCepId) {
    Boolean isServiceAvailable = getServiceAvailability.checkServiceAvailability(serviceId, cep);

    String data = String.format("[\"%s\",\"%s\",\"%s\"]", serviceId, cep, isServiceAvailable);

    logCepService.createLogCep(userCepId, cep, "CHECK_AVAILABILITY", data);

    return isServiceAvailable;
  }

  public ServiceAvailabilityDTO updateServiceAvailability(
      String id, ServiceAvailabilityForm serviceAvailability) {
    return converterToDTO.toDto(updateServiceAvailability.execute(id, serviceAvailability));
  }

  public Page<ServiceAvailabilityDTO> findServiceAvailabilityByCep(String cep, Pageable pageable) {
    return getServiceAvailability.getByCep(cep, pageable).map(converterToDTO::toDto);
  }

  public void deleteServiceAvailability(String id) {
    deleteServiceAvailability.deleteServiceAvailability(id);
  }
}
