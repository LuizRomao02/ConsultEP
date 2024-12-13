package com.luizromao.consultep.application.usecase.update;

import com.luizromao.consultep.application.dto.record.ServiceCepForm;
import com.luizromao.consultep.application.usecase.get.GetServiceCepUseCase;
import com.luizromao.consultep.domain.model.ServiceCep;
import com.luizromao.consultep.domain.repository.ServiceCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateServiceCepUseCase {

  private final ServiceCepRepository serviceCepRepository;
  private final GetServiceCepUseCase getServiceCep;

  @Transactional
  public ServiceCep execute(String id, ServiceCepForm form) {
    ServiceCep serviceCep = getServiceCep.getById(id);

    serviceCep.setServiceType(form.serviceType());
    serviceCep.setName(form.name());
    serviceCep.setDescription(form.description());

    return serviceCepRepository.save(serviceCep);
  }
}
