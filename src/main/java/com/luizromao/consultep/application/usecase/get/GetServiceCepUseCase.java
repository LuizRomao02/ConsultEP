package com.luizromao.consultep.application.usecase.get;

import com.luizromao.consultep.application.dto.ServiceCepDTO;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.ServiceCep;
import com.luizromao.consultep.domain.repository.ServiceCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetServiceCepUseCase {

  private final ServiceCepRepository serviceCepRepository;
  private final ConverterToDTO converterToDTO;

  public Page<ServiceCepDTO> getAll(Pageable pageable) {
    return serviceCepRepository.findAll(pageable).map(converterToDTO::toDto);
  }

  public ServiceCep getById(String id) {
    return serviceCepRepository.findById(id).orElseThrow(RuntimeException::new);
  }
}
