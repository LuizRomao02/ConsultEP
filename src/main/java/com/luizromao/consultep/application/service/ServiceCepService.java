package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.ServiceCepDTO;
import com.luizromao.consultep.application.dto.record.ServiceCepForm;
import com.luizromao.consultep.application.usecase.create.CreateServiceCepUseCase;
import com.luizromao.consultep.application.usecase.delete.DeleteServiceCepUseCase;
import com.luizromao.consultep.application.usecase.get.GetServiceCepUseCase;
import com.luizromao.consultep.application.usecase.update.UpdateServiceCepUseCase;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.ServiceCep;
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

  public ServiceCepDTO createServiceCep(ServiceCepForm serviceCepForm) {
    return converterToDTO.toDto(createServiceCep.execute(serviceCepForm));
  }

  public ServiceCepDTO updateServiceCep(String id, ServiceCepForm serviceCep) {
    return converterToDTO.toDto(updateServiceCep.execute(id, serviceCep));
  }

  public void deleteServiceCep(String id) {
    deleteServiceCep.deleteServiceCep(id);
  }

  @Transactional(readOnly = true)
  public Page<ServiceCepDTO> findAllServiceCep(Pageable pageable) {
    return getServiceCep.getAll(pageable).map(converterToDTO::toDto);
  }

  @Transactional(readOnly = true)
  public ServiceCep getById(String id) {
    return getServiceCep.getById(id);
  }
}
