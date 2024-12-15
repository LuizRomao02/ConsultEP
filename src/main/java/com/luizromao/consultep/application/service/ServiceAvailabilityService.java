package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.CepApiMockDTO;
import com.luizromao.consultep.application.dto.ServiceAvailabilityDTO;
import com.luizromao.consultep.application.dto.ServiceAvailabilityDetailsDTO;
import com.luizromao.consultep.application.dto.record.ServiceAvailabilityForm;
import com.luizromao.consultep.application.usecase.create.CreateServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.delete.DeleteServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.get.GetServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.update.UpdateServiceAvailabilityUseCase;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.application.validation.ValidCep;
import com.luizromao.consultep.domain.model.ServiceAvailability;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceAvailabilityService {

  private final CreateServiceAvailabilityUseCase createServiceAvailability;
  private final DeleteServiceAvailabilityUseCase deleteServiceAvailability;
  private final UpdateServiceAvailabilityUseCase updateServiceAvailability;
  private final GetServiceAvailabilityUseCase getServiceAvailability;
  private final CepApiMockService cepApiMockService;
  private final ConverterToDTO converterToDTO;
  private final LogCepService logCepService;

  public ServiceAvailabilityDTO create(ServiceAvailabilityForm form) {
    return converterToDTO.toDto(createServiceAvailability.create(form));
  }

  @Transactional(readOnly = true)
  public Page<ServiceAvailabilityDTO> findAllServiceAvailability(Pageable pageable) {
    return getServiceAvailability.findAllServiceAvailability(pageable).map(converterToDTO::toDto);
  }

  @Transactional(readOnly = true)
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

  @Transactional(readOnly = true)
  public Page<ServiceAvailabilityDTO> findServiceAvailabilityByCep(String cep, Pageable pageable) {
    return getServiceAvailability.getByCep(cep, pageable).map(converterToDTO::toDto);
  }

  public void deleteServiceAvailability(String id) {
    deleteServiceAvailability.deleteServiceAvailability(id);
  }

  @Transactional(readOnly = true)
  public ServiceAvailabilityDetailsDTO findDetailsServiceAvailability(String id) {
    ServiceAvailability service = getServiceAvailability.getById(id);

    CepApiMockDTO infoCep = cepApiMockService.fetchCepApiMock(service.getCep());

    ServiceAvailabilityDetailsDTO dto = converterToDTO.toDetailsDto(service);
    dto.setInfoCep(infoCep);

    return dto;
  }
}
