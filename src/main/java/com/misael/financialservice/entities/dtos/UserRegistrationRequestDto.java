package com.misael.financialservice.entities.dtos;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import lombok.Builder;

/**
 * UserRegistrationRequest
 */
@Builder
public record UserRegistrationRequestDto(
    String completeName,
    @Email(message = "Email inválido")
    String email,
    @CPF(message = "CPF inválido")
    @CNPJ(message = "CNPJ inválido")
    String document,
    Double wallet
) {
}