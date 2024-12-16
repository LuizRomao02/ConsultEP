package com.luizromao.consultep.application.dto.record;

import com.luizromao.consultep.application.validation.ValidCep;

public record ServiceAvailabilityForm(
        String userCepBy, String serviceCepId, @ValidCep String cep, boolean availabilityStatus) {}
