package com.luizromao.consultep.application.util;

import com.luizromao.consultep.application.dto.LogCepDTO;
import com.luizromao.consultep.application.dto.ServiceAvailabilityDTO;
import com.luizromao.consultep.application.dto.ServiceCepDTO;
import com.luizromao.consultep.application.dto.UserCepDTO;
import com.luizromao.consultep.domain.model.LogCep;
import com.luizromao.consultep.domain.model.ServiceAvailability;
import com.luizromao.consultep.domain.model.ServiceCep;
import com.luizromao.consultep.domain.model.UserCep;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterToDTO {

  private final ModelMapper modelMapper;

  public LogCepDTO toDto(final LogCep model) {
    return modelMapper.map(model, LogCepDTO.class);
  }

  public UserCepDTO toDto(final UserCep model) {
    return modelMapper.map(model, UserCepDTO.class);
  }

  public ServiceCepDTO toDto(final ServiceCep model) {
    return modelMapper.map(model, ServiceCepDTO.class);
  }

  public ServiceAvailabilityDTO toDto(final ServiceAvailability model) {
    return modelMapper.map(model, ServiceAvailabilityDTO.class);
  }
}
