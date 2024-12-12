package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.ServiceAvailabilityDTO;
import com.luizromao.consultep.application.dto.record.ServiceAvailabilityForm;
import com.luizromao.consultep.application.usecase.create.CreateServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.get.GetServiceAvailabilityUseCase;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.application.validation.ValidCep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceAvailabilityService {

  private final CreateServiceAvailabilityUseCase createServiceAvailabilityUseCase;
  private final GetServiceAvailabilityUseCase getServiceAvailabilityUseCase;
  private final LogCepService logCepService;
  private final ConverterToDTO converterToDTO;

  public ServiceAvailabilityService(
      CreateServiceAvailabilityUseCase createServiceAvailabilityUseCase,
      GetServiceAvailabilityUseCase getServiceAvailabilityUseCase,
      LogCepService logCepService,
      ConverterToDTO converterToDTO) {
    this.createServiceAvailabilityUseCase = createServiceAvailabilityUseCase;
    this.getServiceAvailabilityUseCase = getServiceAvailabilityUseCase;
    this.logCepService = logCepService;
    this.converterToDTO = converterToDTO;
  }

  public ServiceAvailabilityDTO create(ServiceAvailabilityForm form) {
    return converterToDTO.toDto(createServiceAvailabilityUseCase.create(form));
  }

  public Page<ServiceAvailabilityDTO> findAllServiceAvailability(Pageable pageable) {
    return getServiceAvailabilityUseCase
        .findAllServiceAvailability(pageable)
        .map(converterToDTO::toDto);
  }

  public Boolean checkAvailability(String serviceId, @ValidCep String cep, String userCepId) {
    Boolean isServiceAvailable =
        getServiceAvailabilityUseCase.checkServiceAvailability(serviceId, cep);

    String data = String.format("[\"%s\",\"%s\",\"%s\"]", serviceId, cep, isServiceAvailable);

    logCepService.createLogCep(userCepId, cep, "CHECK_AVAILABILITY", data);

    return isServiceAvailable;
  }
}
