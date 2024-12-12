package com.luizromao.consultep.presentation.controller;

import com.luizromao.consultep.application.dto.ServiceCepDTO;
import com.luizromao.consultep.application.dto.record.ServiceCepForm;
import com.luizromao.consultep.application.service.ServiceCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service_cep")
@RequiredArgsConstructor
public class ServiceCepController {

  private final ServiceCepService serviceCepService;

  @PostMapping
  public ResponseEntity<ServiceCepDTO> create(@RequestBody ServiceCepForm serviceCep) {
    ServiceCepDTO createdServiceCep = serviceCepService.createServiceCep(serviceCep);
    return ResponseEntity.status(201).body(createdServiceCep);
  }

  @GetMapping
  public ResponseEntity<Page<ServiceCepDTO>> getAll(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(serviceCepService.findAllServiceCep(pageable));
  }
}
