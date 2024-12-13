package com.luizromao.consultep.application.usecase.get;

import com.luizromao.consultep.domain.model.LogCep;
import com.luizromao.consultep.domain.repository.LogCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetLogCepUseCase {

  private final LogCepRepository logCepRepository;

  @Transactional(readOnly = true)
  public Page<LogCep> getLogByUser(String userCepId, Pageable pageable) {
    return logCepRepository.findByUserCepId(userCepId, pageable);
  }

  @Transactional(readOnly = true)
  public Page<LogCep> getLogByCep(String cep, Pageable pageable) {
    return logCepRepository.findByCep(cep, pageable);
  }
}
