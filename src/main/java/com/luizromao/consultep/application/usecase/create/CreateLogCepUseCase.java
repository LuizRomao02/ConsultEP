package com.luizromao.consultep.application.usecase.create;

import com.luizromao.consultep.application.dto.LogCepDTO;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.LogCep;
import com.luizromao.consultep.domain.model.UserCep;
import com.luizromao.consultep.domain.repository.LogCepRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateLogCepUseCase {

  private final LogCepRepository logCepRepository;
  private final ConverterToDTO converterToDTO;

  public CreateLogCepUseCase(LogCepRepository logCepRepository, ConverterToDTO converterToDTO) {
    this.logCepRepository = logCepRepository;
    this.converterToDTO = converterToDTO;
  }

  @Transactional
  public LogCepDTO execute(String userId, String cep, String requestType) {
    LogCep log =
        LogCep.builder()
            .userCep(UserCep.builder().build())
            .cep(cep)
            .requestType(requestType)
            .build();

    return converterToDTO.toDto(logCepRepository.save(log));
  }
}
