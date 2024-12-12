package com.luizromao.consultep.application.usecase.delete;

import com.luizromao.consultep.domain.repository.ServiceCepRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteServiceUseCase {

  private final ServiceCepRepository serviceCepRepository;

  public DeleteServiceUseCase(ServiceCepRepository serviceCepRepository) {
    this.serviceCepRepository = serviceCepRepository;
  }

  @Transactional
  public void execute(String serviceId) {
    serviceCepRepository.deleteById(serviceId);
  }
}
