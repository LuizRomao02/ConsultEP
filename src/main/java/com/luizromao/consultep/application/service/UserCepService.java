package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.UserCepDTO;
import com.luizromao.consultep.application.dto.record.UserCepForm;
import com.luizromao.consultep.application.usecase.create.CreateUserUseCase;
import com.luizromao.consultep.application.usecase.get.GetUserCepUseCase;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.UserCep;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCepService {

  private final CreateUserUseCase createUserUseCase;
  private final GetUserCepUseCase getUserCepUseCase;
  private final ConverterToDTO converterToDTO;

  public UserCepDTO createUserCep(UserCepForm userCepForm) {
    return converterToDTO.toDto(createUserUseCase.execute(userCepForm));
  }

  public Page<UserCepDTO> findAllUserCep(Pageable pageable) {
    return getUserCepUseCase.getAll(pageable).map(converterToDTO::toDto);
  }

  public UserCep findUserCepById(String id) {
    return getUserCepUseCase.getById(id);
  }

  public UserCepDTO getUserCep(String id) {
    return converterToDTO.toDto(findUserCepById(id));
  }
}
