package com.luizromao.consultep.application.usecase.get;

import com.luizromao.consultep.application.exception.ServiceCepException;
import com.luizromao.consultep.domain.model.ServiceCep;
import com.luizromao.consultep.domain.repository.ServiceCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetServiceCepUseCase {

  private final ServiceCepRepository serviceCepRepository;

  @Transactional(readOnly = true)
  public Page<ServiceCep> getAll(Pageable pageable) {
    return serviceCepRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public ServiceCep getById(String id) {
    return serviceCepRepository
        .findById(id)
        .orElseThrow(() -> new ServiceCepException(HttpStatus.NOT_FOUND, "Service cep not found"));
  }
}
