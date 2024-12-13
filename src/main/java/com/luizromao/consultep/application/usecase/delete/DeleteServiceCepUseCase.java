package com.luizromao.consultep.application.usecase.delete;

import com.luizromao.consultep.application.usecase.get.GetServiceCepUseCase;
import com.luizromao.consultep.domain.repository.ServiceCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteServiceCepUseCase {

  private final ServiceCepRepository serviceCepRepository;
  private final GetServiceCepUseCase getServiceCepUseCase;

  @Transactional
  public void deleteServiceCep(String id) {
    var serviceCep = getServiceCepUseCase.getById(id);
    serviceCepRepository.delete(serviceCep);
  }
}
