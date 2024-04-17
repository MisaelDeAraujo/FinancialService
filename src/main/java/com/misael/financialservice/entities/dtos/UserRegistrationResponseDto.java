package com.misael.financialservice.entities.dtos;

import lombok.Builder;

/**
 * UserRegistrationResponseDto
 */
@Builder
public record UserRegistrationResponseDto(
    Integer id,
    String completeName,
    String document,
    Double wallet
) {
}