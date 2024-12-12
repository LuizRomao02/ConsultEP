package com.luizromao.consultep.application.usecase.create;

import com.luizromao.consultep.application.dto.UserCepDTO;
import com.luizromao.consultep.application.dto.record.UserCepForm;
import com.luizromao.consultep.application.util.ConverterToDTO;
import com.luizromao.consultep.domain.model.UserCep;
import com.luizromao.consultep.domain.repository.UserCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

  private final UserCepRepository userCepRepository;
  private final ConverterToDTO converterToDTO;

  @Transactional
  public UserCepDTO execute(UserCepForm userCepForm) {
    if (userCepForm.email() == null || !userCepForm.email().contains("@")) {
      throw new IllegalArgumentException("Email not validate");
    }

    UserCep user = UserCep.builder().name(userCepForm.name()).email(userCepForm.email()).build();

    return converterToDTO.toDto(userCepRepository.save(user));
  }
}
