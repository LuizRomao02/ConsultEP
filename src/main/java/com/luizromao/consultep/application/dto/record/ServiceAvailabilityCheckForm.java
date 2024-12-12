package com.luizromao.consultep.application.dto.record;

import com.luizromao.consultep.application.validation.ValidCep;

public record ServiceAvailabilityCheckForm(
    String serviceCepId, @ValidCep String cep, String userCepId) {}
