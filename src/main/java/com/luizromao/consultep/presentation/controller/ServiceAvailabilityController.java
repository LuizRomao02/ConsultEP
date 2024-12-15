package com.luizromao.consultep.presentation.controller;

import com.luizromao.consultep.application.dto.ServiceAvailabilityDTO;
import com.luizromao.consultep.application.dto.ServiceAvailabilityDetailsDTO;
import com.luizromao.consultep.application.dto.record.ServiceAvailabilityCheckForm;
import com.luizromao.consultep.application.dto.record.ServiceAvailabilityForm;
import com.luizromao.consultep.application.service.ServiceAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service_availability")
@RequiredArgsConstructor
public class ServiceAvailabilityController {

  private final ServiceAvailabilityService serviceAvailabilityService;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ServiceAvailabilityDTO> create(@RequestBody ServiceAvailabilityForm form) {
    var createdServiceCep = serviceAvailabilityService.create(form);
    return ResponseEntity.status(HttpStatus.OK).body(createdServiceCep);
  }

  @PostMapping(
      value = "/check_availability",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> checkAvailability(@RequestBody ServiceAvailabilityCheckForm form) {
    return ResponseEntity.ok(
        serviceAvailabilityService.checkAvailability(
            form.serviceCepId(), form.cep(), form.userCepId()));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<ServiceAvailabilityDTO>> getAll(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(serviceAvailabilityService.findAllServiceAvailability(pageable));
  }

  @GetMapping(value = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ServiceAvailabilityDetailsDTO> getServiceDetails(@PathVariable String id) {
    return ResponseEntity.ok(serviceAvailabilityService.findDetailsServiceAvailability(id));
  }

  @GetMapping(value = "/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<ServiceAvailabilityDTO>> getServiceAvailabilityByCep(
      @PathVariable String cep, @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(
        serviceAvailabilityService.findServiceAvailabilityByCep(cep, pageable));
  }

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ServiceAvailabilityDTO> update(
      @PathVariable String id, @RequestBody ServiceAvailabilityForm serviceCep) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(serviceAvailabilityService.updateServiceAvailability(id, serviceCep));
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
    serviceAvailabilityService.deleteServiceAvailability(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
