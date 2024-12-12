package com.luizromao.consultep.application.dto.record;

public record ServiceAvailabilityForm(
    String serviceCepId, String cep, boolean availabilityStatus) {}
