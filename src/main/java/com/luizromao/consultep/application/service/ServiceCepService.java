package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.ServiceCepDTO;
import com.luizromao.consultep.application.dto.record.ServiceCepForm;
import com.luizromao.consultep.application.usecase.create.CreateServiceCepUseCase;
import com.luizromao.consultep.application.usecase.delete.DeleteServiceCepUseCase;
import com.luizromao.consultep.application.usecase.get.GetServiceCepUseCase;
import com.luizromao.consultep.application.usecase.update.UpdateServiceCepUseCase;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.ServiceCep;
import com.luizromao.consultep.domain.model.UserCep;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceCepService {

  private final CreateServiceCepUseCase createServiceCep;
  private final UpdateServiceCepUseCase updateServiceCep;
  private final DeleteServiceCepUseCase deleteServiceCep;
  private final GetServiceCepUseCase getServiceCep;
  private final ConverterToDTO converterToDTO;
  private final UserCepService userCepService;
  private final LogCepService logCepService;

  public ServiceCepDTO createServiceCep(ServiceCepForm serviceCepForm) {
    ServiceCep serviceCep = createServiceCep.execute(serviceCepForm);
    ServiceCepDTO serviceCepDto = converterToDTO.toDto(serviceCep);

    logCepService.createLogCep(
        serviceCep.getCreatedBy(), null, "CREATE_SERVICE_CEP", serviceCepDto);

    return serviceCepDto;
  }

  public ServiceCepDTO updateServiceCep(String id, ServiceCepForm serviceCep) {
    ServiceCep updatedServiceCep = updateServiceCep.execute(id, serviceCep);
    ServiceCepDTO updatedServiceCepDto = converterToDTO.toDto(updatedServiceCep);

    logCepService.createLogCep(
        updatedServiceCep.getCreatedBy(), null, "UPDATE_SERVICE_CEP", updatedServiceCepDto);

    return updatedServiceCepDto;
  }

  public void deleteServiceCep(String userById, String id) {
    ServiceCep serviceCep = getServiceCep.getById(id);
    UserCep userCep = userCepService.getById(userById);
    var serviceId = serviceCep.getId();

    deleteServiceCep.deleteServiceCep(id);

    logCepService.createLogCep(userCep, null, "DELETE_SERVICE_CEP", "ID: " + serviceId);
  }

  public Page<ServiceCepDTO> findAllServiceCep(Pageable pageable) {
    Page<ServiceCep> servicesCep = getServiceCep.getAll(pageable);

    logCepService.createLogCep(
        null, null, "FIND_ALL_SERVICE_CEP", servicesCep.stream().map(ServiceCep::getId).toList());

    return servicesCep.map(converterToDTO::toDto);
  }

  public ServiceCep getById(String id) {
    return getServiceCep.getById(id);
  }
}
