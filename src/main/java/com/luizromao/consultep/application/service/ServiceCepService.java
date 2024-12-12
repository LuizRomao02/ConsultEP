package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.ServiceCepDTO;
import com.luizromao.consultep.application.dto.record.ServiceCepForm;
import com.luizromao.consultep.application.usecase.create.CreateServiceCepUseCase;
import com.luizromao.consultep.application.usecase.get.GetServiceCepUseCase;
import com.luizromao.consultep.domain.model.ServiceCep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceCepService {

  private final CreateServiceCepUseCase createServiceCep;
  private final GetServiceCepUseCase getServiceCep;

  public ServiceCepService(
      CreateServiceCepUseCase createServiceCep, GetServiceCepUseCase getServiceCep) {
    this.createServiceCep = createServiceCep;
    this.getServiceCep = getServiceCep;
  }

  public ServiceCepDTO createServiceCep(ServiceCepForm serviceCepForm) {
    return createServiceCep.execute(serviceCepForm);
  }

  public Page<ServiceCepDTO> findAllServiceCep(Pageable pageable) {
    return getServiceCep.getAll(pageable);
  }

  public ServiceCep findServiceCepById(String id) {
    return getServiceCep.getById(id);
  }
}
