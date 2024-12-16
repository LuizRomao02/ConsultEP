package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.CepApiMockDTO;
import com.luizromao.consultep.application.dto.CheckServiceAvailabilityDTO;
import com.luizromao.consultep.application.dto.ServiceAvailabilityDTO;
import com.luizromao.consultep.application.dto.ServiceAvailabilityDetailsDTO;
import com.luizromao.consultep.application.dto.record.ServiceAvailabilityForm;
import com.luizromao.consultep.application.exception.ServiceAvailabilityException;
import com.luizromao.consultep.application.usecase.create.CreateServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.delete.DeleteServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.get.GetServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.update.UpdateServiceAvailabilityUseCase;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.BaseEntity;
import com.luizromao.consultep.domain.model.ServiceAvailability;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
  private final UserCepService userCepService;
  private final LogCepService logCepService;

  public ServiceAvailabilityDTO create(ServiceAvailabilityForm form) {
    var service = createServiceAvailability.create(form);
    var serviceDto = converterToDTO.toDto(service);

    logCepService.createLogCep(
        service.getCreatedBy(), service.getCep(), "CREATE_SERVICE_AVAILABILITY", serviceDto);

    return serviceDto;
  }

  public Page<ServiceAvailabilityDTO> findAllServiceAvailability(Pageable pageable) {
    var service = getServiceAvailability.findAllServiceAvailability(pageable);

    logCepService.createLogCep(
        null,
        null,
        "FIND_ALL_SERVICE_AVAILABILITY",
        service.stream().map(BaseEntity::getId).toList());

    return service.map(converterToDTO::toDto);
  }

  public List<CheckServiceAvailabilityDTO> checkAvailability(
      String serviceId, String cep, String userCepId) {

    List<ServiceAvailability> serviceAvailable =
        getServiceAvailability.checkServiceAvailability(serviceId, cep);

    if (serviceAvailable.isEmpty()) {
      throw new ServiceAvailabilityException(
          HttpStatus.NOT_FOUND, "Not Found Service Availability with this cep and service");
    }

    var userCep = userCepService.getById(userCepId);
    var dto = serviceAvailable.stream().map(converterToDTO::toCheckDto).toList();

    logCepService.createLogCep(userCep, cep, "CHECK_SERVICE_AVAILABILITY", dto);

    return dto;
  }

  public ServiceAvailabilityDTO updateServiceAvailability(
      String id, ServiceAvailabilityForm serviceAvailability) {
    var updatedService = updateServiceAvailability.execute(id, serviceAvailability);
    var updatedServiceDto = converterToDTO.toDto(updatedService);
    var userBy = userCepService.getById(serviceAvailability.userCepBy());

    logCepService.createLogCep(
        userBy, updatedService.getCep(), "UPDATE_SERVICE_AVAILABILITY", updatedServiceDto);

    return updatedServiceDto;
  }

  public Page<ServiceAvailabilityDTO> findServiceAvailabilityByCep(String cep, Pageable pageable) {
    var services = getServiceAvailability.getByCep(cep, pageable);

    logCepService.createLogCep(null, cep, "FIND_SERVICE_AVAILABILITY_BY_CEP", services);

    return services.map(converterToDTO::toDto);
  }

  public void deleteServiceAvailability(String userById, String id) {
    var service = getServiceAvailability.getById(id);
    var serviceId = "ID: " + service.getId();
    var userBy = userCepService.getById(userById);

    deleteServiceAvailability.deleteServiceAvailability(id);

    logCepService.createLogCep(userBy, service.getCep(), "DELETE_SERVICE_AVAILABILITY", serviceId);
  }

  public ServiceAvailabilityDetailsDTO findDetailsServiceAvailability(String id) {
    ServiceAvailability service = getServiceAvailability.getById(id);

    CepApiMockDTO infoCep = cepApiMockService.fetchCepApiMock(service.getCep());
    ServiceAvailabilityDetailsDTO dto = converterToDTO.toDetailsDto(service);
    dto.getServiceCep().setInfoCep(infoCep);

    logCepService.createLogCep(
        service.getCreatedBy(), service.getCep(), "FIND_DETAILS_SERVICE_AVAILABILITY", dto);

    return dto;
  }
}
