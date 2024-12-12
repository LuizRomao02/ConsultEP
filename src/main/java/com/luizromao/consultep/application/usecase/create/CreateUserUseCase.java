package com.luizromao.consultep.application.usecase.create;

import com.luizromao.consultep.application.dto.record.UserCepForm;
import com.luizromao.consultep.domain.model.UserCep;
import com.luizromao.consultep.domain.repository.UserCepRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserUseCase {

  private final UserCepRepository userCepRepository;

  public CreateUserUseCase(UserCepRepository userCepRepository) {
    this.userCepRepository = userCepRepository;
  }

  @Transactional
  public UserCep execute(UserCepForm userCepForm) {
    if (userCepForm.email() == null || !userCepForm.email().contains("@")) {
      throw new IllegalArgumentException("Email not validate");
    }

    UserCep user = UserCep.builder().name(userCepForm.name()).email(userCepForm.email()).build();

    return userCepRepository.save(user);
  }
}
