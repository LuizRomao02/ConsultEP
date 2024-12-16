package com.luizromao.consultep.application.usecase.create;

import com.luizromao.consultep.application.dto.record.ServiceCepForm;
import com.luizromao.consultep.application.service.UserCepService;
import com.luizromao.consultep.domain.model.ServiceCep;
import com.luizromao.consultep.domain.repository.ServiceCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateServiceCepUseCase {

  private final ServiceCepRepository serviceCepRepository;
  private final UserCepService userCepService;

  @Transactional
  public ServiceCep execute(ServiceCepForm serviceCepForm) {
    var userBy = userCepService.getById(serviceCepForm.userCepBy());

    ServiceCep service =
        ServiceCep.builder()
            .serviceType(serviceCepForm.serviceType())
            .name(serviceCepForm.name())
            .createdBy(userBy)
            .description(serviceCepForm.description())
            .build();

    return serviceCepRepository.save(service);
  }
}
