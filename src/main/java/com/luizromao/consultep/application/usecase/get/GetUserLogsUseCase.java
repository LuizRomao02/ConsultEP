package com.luizromao.consultep.application.usecase.get;

import com.luizromao.consultep.application.dto.LogCepDTO;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.repository.LogCepRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserLogsUseCase {

  private final LogCepRepository logCepRepository;
  private final ConverterToDTO converterToDTO;

  public List<LogCepDTO> execute(String userCepId) {
    return logCepRepository.findByUserCepId(userCepId).stream().map(converterToDTO::toDto).toList();
  }
}
