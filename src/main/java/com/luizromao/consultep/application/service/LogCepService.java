package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.LogCepDTO;
import com.luizromao.consultep.application.usecase.create.CreateLogCepUseCase;
import com.luizromao.consultep.application.usecase.get.GetLogCepUseCase;
import com.luizromao.consultep.application.util.ConverterToDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogCepService {

  private final CreateLogCepUseCase createLogCepUseCase;
  private final GetLogCepUseCase getLogCepUseCase;
  private final UserCepService userCepService;
  private final ConverterToDTO converterToDTO;

  public void createLogCep(String userCepId, String cep, String requestType, String data) {
    var userCep = userCepService.findUserCepById(userCepId);
    createLogCepUseCase.execute(userCep, cep, requestType, data);
  }

  public Page<LogCepDTO> getLogByUser(String userCepId, Pageable pageable) {
    return getLogCepUseCase.getLogByUser(userCepId, pageable).map(converterToDTO::toDto);
  }

  public Page<LogCepDTO> getLogByCep(String cep, Pageable pageable) {
    return getLogCepUseCase.getLogByCep(cep, pageable).map(converterToDTO::toDto);
  }
}
