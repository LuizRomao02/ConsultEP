package com.luizromao.consultep.application.usecase.get;

import com.luizromao.consultep.application.exception.UserCepException;
import com.luizromao.consultep.domain.model.UserCep;
import com.luizromao.consultep.domain.repository.UserCepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetUserCepUseCase {

  private final UserCepRepository userCepRepository;

  @Transactional(readOnly = true)
  public Page<UserCep> getAll(Pageable pageable) {
    return userCepRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public UserCep getById(String id) {
    return userCepRepository
        .findById(id)
        .orElseThrow(() -> new UserCepException(HttpStatus.NOT_FOUND, "User cep not found"));
  }
}
