package com.misael.financialservice.entities.dtos;

import lombok.Builder;

/**
 * UserRegistrationRequest
 */
@Builder
public record UserRegistrationRequestDto(
    String completeName,
    String email,
    String document,
    Double wallet
) {
}