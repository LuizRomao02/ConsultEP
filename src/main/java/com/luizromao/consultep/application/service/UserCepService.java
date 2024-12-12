package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.dto.UserCepDTO;
import com.luizromao.consultep.application.dto.record.UserCepForm;
import com.luizromao.consultep.application.usecase.create.CreateUserUseCase;
import com.luizromao.consultep.application.usecase.get.GetUserCepUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserCepService {

  private final CreateUserUseCase createUserUseCase;
  private final GetUserCepUseCase getUserCepUseCase;

  public UserCepService(CreateUserUseCase createUserUseCase, GetUserCepUseCase getUserCepUseCase) {
    this.createUserUseCase = createUserUseCase;
    this.getUserCepUseCase = getUserCepUseCase;
  }

  public UserCepDTO createUserCep(UserCepForm userCepForm) {
    return createUserUseCase.execute(userCepForm);
  }

  public Page<UserCepDTO> findAllUserCep(Pageable pageable) {
    return getUserCepUseCase.getAll(pageable);
  }
}
