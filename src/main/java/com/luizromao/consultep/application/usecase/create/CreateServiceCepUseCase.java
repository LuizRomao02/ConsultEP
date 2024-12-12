package com.luizromao.consultep.application.usecase.create;

import com.luizromao.consultep.application.dto.ServiceCepDTO;
import com.luizromao.consultep.application.dto.record.ServiceCepForm;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.ServiceCep;
import com.luizromao.consultep.domain.repository.ServiceCepRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateServiceCepUseCase {

  private final ServiceCepRepository serviceCepRepository;
  private final ConverterToDTO converterToDTO;

  public CreateServiceCepUseCase(
      ServiceCepRepository serviceCepRepository, ConverterToDTO converterToDTO) {
    this.serviceCepRepository = serviceCepRepository;
    this.converterToDTO = converterToDTO;
  }

  @Transactional
  public ServiceCepDTO execute(ServiceCepForm serviceCepForm) {
    ServiceCep service =
        ServiceCep.builder()
            .serviceType(serviceCepForm.serviceType())
            .name(serviceCepForm.name())
            .description(serviceCepForm.description())
            .build();

    return converterToDTO.toDto(serviceCepRepository.save(service));
  }
}
