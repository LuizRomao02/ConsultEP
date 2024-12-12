package com.luizromao.consultep.application.usecase.get;

import com.luizromao.consultep.domain.model.LogCep;
import com.luizromao.consultep.domain.repository.LogCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLogCepUseCase {

  private final LogCepRepository logCepRepository;

  public Page<LogCep> getLogByUser(String userCepId, Pageable pageable) {
    return logCepRepository.findByUserCepId(userCepId, pageable);
  }

  public Page<LogCep> getLogByCep(String cep, Pageable pageable) {
    return logCepRepository.findByCep(cep, pageable);
  }
}
