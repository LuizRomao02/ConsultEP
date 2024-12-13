package com.luizromao.consultep.presentation.controller;

import com.luizromao.consultep.application.dto.UserCepDTO;
import com.luizromao.consultep.application.dto.record.UserCepForm;
import com.luizromao.consultep.application.service.UserCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user_cep")
@RequiredArgsConstructor
public class UserCepController {

  private final UserCepService userCepService;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserCepDTO> create(@RequestBody UserCepForm userCepForm) {
    UserCepDTO createdServiceCep = userCepService.createUserCep(userCepForm);
    return ResponseEntity.status(201).body(createdServiceCep);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<UserCepDTO>> getAll(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(userCepService.findAllUserCep(pageable));
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserCepDTO> getById(@PathVariable String id) {
    return ResponseEntity.ok(userCepService.getUserCep(id));
  }
}
