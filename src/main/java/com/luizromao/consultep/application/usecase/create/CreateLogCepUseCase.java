package com.luizromao.consultep.application.usecase.create;

import com.luizromao.consultep.domain.model.LogCep;
import com.luizromao.consultep.domain.model.UserCep;
import com.luizromao.consultep.domain.repository.LogCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateLogCepUseCase {

  private final LogCepRepository logCepRepository;

  @Transactional
  public void execute(UserCep userCep, String cep, String requestType, String data) {
    LogCep log =
        LogCep.builder().userCep(userCep).cep(cep).requestType(requestType).jsonData(data).build();

    logCepRepository.save(log);
  }
}
