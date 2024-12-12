package com.luizromao.consultep.application.usecase.get;

import com.luizromao.consultep.domain.model.UserCep;
import com.luizromao.consultep.domain.repository.UserCepRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetUserCepUseCase {

  private final UserCepRepository userCepRepository;

  public GetUserCepUseCase(UserCepRepository userCepRepository) {
    this.userCepRepository = userCepRepository;
  }

  public Page<UserCep> getAll(Pageable pageable) {
    return userCepRepository.findAll(pageable);
  }

  public UserCep getById(String id) {
    return userCepRepository.findById(id).orElse(null);
  }
}
