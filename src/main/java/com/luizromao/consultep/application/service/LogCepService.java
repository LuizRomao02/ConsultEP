package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.LogCepDTO;
import com.luizromao.consultep.application.usecase.create.CreateLogCepUseCase;
import com.luizromao.consultep.application.usecase.get.GetLogCepUseCase;
import com.luizromao.consultep.application.util.ConverterToDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogCepService {

  private final CreateLogCepUseCase createLogCepUseCase;
  private final UserCepService userCepService;
  private final GetLogCepUseCase getLogCepUseCase;
  private final ConverterToDTO converterToDTO;

  public LogCepService(
      CreateLogCepUseCase createLogCepUseCase,
      UserCepService userCepService,
      GetLogCepUseCase getLogCepUseCase,
      ConverterToDTO converterToDTO) {
    this.createLogCepUseCase = createLogCepUseCase;
    this.userCepService = userCepService;
    this.getLogCepUseCase = getLogCepUseCase;
    this.converterToDTO = converterToDTO;
  }

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
