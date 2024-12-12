package com.luizromao.consultep.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CepValidator implements ConstraintValidator<ValidCep, String> {

  private static final String CEP_PATTERN = "^[0-9]{8}$";

  @Override
  public boolean isValid(String cep, ConstraintValidatorContext context) {
    if (cep == null || cep.isEmpty()) {
      return false;
    }
    return cep.matches(CEP_PATTERN);
  }
}
