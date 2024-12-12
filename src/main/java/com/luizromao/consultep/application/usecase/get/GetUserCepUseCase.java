package com.luizromao.consultep.application.usecase.get;

import com.luizromao.consultep.application.dto.UserCepDTO;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.repository.UserCepRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetUserCepUseCase {

  private final UserCepRepository userCepRepository;
  private final ConverterToDTO converterToDTO;

  public GetUserCepUseCase(UserCepRepository userCepRepository, ConverterToDTO converterToDTO) {
    this.userCepRepository = userCepRepository;
    this.converterToDTO = converterToDTO;
  }

  public Page<UserCepDTO> getAll(Pageable pageable) {
    return userCepRepository.findAll(pageable).map(converterToDTO::toDto);
  }
}
