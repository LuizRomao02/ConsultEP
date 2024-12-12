package com.luizromao.consultep.application.service;

import com.luizromao.consultep.application.usecase.create.CreateServiceAvailabilityUseCase;
import com.luizromao.consultep.application.usecase.get.GetServiceAvailabilityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceAvailabilityService {

  private final CreateServiceAvailabilityUseCase createServiceAvailabilityUseCase;
  private final GetServiceAvailabilityUseCase getServiceAvailabilityUseCase;
}
