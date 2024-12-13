package com.luizromao.consultep.application.usecase.create;

import com.luizromao.consultep.application.dto.record.ServiceCepForm;
import com.luizromao.consultep.domain.model.ServiceCep;
import com.luizromao.consultep.domain.repository.ServiceCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateServiceCepUseCase {

  private final ServiceCepRepository serviceCepRepository;

  @Transactional
  public ServiceCep execute(ServiceCepForm serviceCepForm) {
    ServiceCep service =
        ServiceCep.builder()
            .serviceType(serviceCepForm.serviceType())
            .name(serviceCepForm.name())
            .description(serviceCepForm.description())
            .build();

    return serviceCepRepository.save(service);
  }
}
