package com.luizromao.consultep.presentation.controller;

import com.luizromao.consultep.application.dto.ServiceCepDTO;
import com.luizromao.consultep.application.dto.record.ServiceCepForm;
import com.luizromao.consultep.application.service.ServiceCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service_cep")
@RequiredArgsConstructor
public class ServiceCepController {

  private final ServiceCepService serviceCepService;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ServiceCepDTO> create(@RequestBody ServiceCepForm serviceCep) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(serviceCepService.createServiceCep(serviceCep));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<ServiceCepDTO>> getAll(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(serviceCepService.findAllServiceCep(pageable));
  }

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ServiceCepDTO> update(
      @PathVariable String id, @RequestBody ServiceCepForm serviceCep) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(serviceCepService.updateServiceCep(id, serviceCep));
  }

  @DeleteMapping(value = "/{userById}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteAuthor(@PathVariable String userById, @PathVariable String id) {
    serviceCepService.deleteServiceCep(userById, id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
