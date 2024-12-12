package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.UserCepDTO;
import com.luizromao.consultep.application.dto.record.UserCepForm;
import com.luizromao.consultep.application.usecase.create.CreateUserUseCase;
import com.luizromao.consultep.application.usecase.get.GetUserCepUseCase;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.UserCep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserCepService {

  private final CreateUserUseCase createUserUseCase;
  private final GetUserCepUseCase getUserCepUseCase;
  private final ConverterToDTO converterToDTO;

  public UserCepService(
      CreateUserUseCase createUserUseCase,
      GetUserCepUseCase getUserCepUseCase,
      ConverterToDTO converterToDTO) {
    this.createUserUseCase = createUserUseCase;
    this.getUserCepUseCase = getUserCepUseCase;
    this.converterToDTO = converterToDTO;
  }

  public UserCepDTO createUserCep(UserCepForm userCepForm) {
    return converterToDTO.toDto(createUserUseCase.execute(userCepForm));
  }

  public Page<UserCepDTO> findAllUserCep(Pageable pageable) {
    return getUserCepUseCase.getAll(pageable).map(converterToDTO::toDto);
  }

  public UserCep findUserCepById(String id) {
    return getUserCepUseCase.getById(id);
  }
}
