package com.luizromao.consultep.presentation.controller;

import com.luizromao.consultep.application.dto.ServiceAvailabilityDTO;
import com.luizromao.consultep.application.dto.record.ServiceAvailabilityCheckForm;
import com.luizromao.consultep.application.dto.record.ServiceAvailabilityForm;
import com.luizromao.consultep.application.service.ServiceAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service_availability")
@RequiredArgsConstructor
public class ServiceAvailabilityController {

  private final ServiceAvailabilityService serviceAvailabilityService;

  @PostMapping
  public ResponseEntity<ServiceAvailabilityDTO> create(@RequestBody ServiceAvailabilityForm form) {
    var createdServiceCep = serviceAvailabilityService.create(form);
    return ResponseEntity.status(201).body(createdServiceCep);
  }

  @GetMapping
  public ResponseEntity<Page<ServiceAvailabilityDTO>> getAll(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(serviceAvailabilityService.findAllServiceAvailability(pageable));
  }

  @PostMapping("/check_availability")
  public ResponseEntity<Boolean> getCheckAvailability(
      @RequestBody ServiceAvailabilityCheckForm form) {
    return ResponseEntity.ok(
        serviceAvailabilityService.checkAvailability(
            form.serviceCepId(), form.cep(), form.userCepId()));
  }
}
