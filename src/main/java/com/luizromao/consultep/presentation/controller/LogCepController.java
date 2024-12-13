package com.luizromao.consultep.presentation.controller;

import com.luizromao.consultep.application.dto.LogCepDTO;
import com.luizromao.consultep.application.service.LogCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log_cep")
@RequiredArgsConstructor
public class LogCepController {

  private final LogCepService logCepService;

  @GetMapping("/user")
  public ResponseEntity<Page<LogCepDTO>> getLogCepByUser(
      @RequestParam("userCepId") String userCepId, @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(logCepService.getLogByUser(userCepId, pageable));
  }

  @GetMapping("/cep")
  public ResponseEntity<Page<LogCepDTO>> getLogCepByCep(
      @RequestParam("cep") String cep, @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(logCepService.getLogByCep(cep, pageable));
  }
}
