package com.misael.financialservice.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import lombok.Builder;

/**
 * UserRegistrationRequest
 */
@Builder
public record UserRegistrationRequestDto(
    @NotNull(message = "Campo completeName não pode ser nulo")
    @NotBlank(message = "Campo completeName não pode ser vazio")
    String completeName,
    @NotNull(message = "Campo email não pode ser nulo")
    @NotBlank(message = "Campo email não pode ser vazio")
    @Email(message = "Email inválido")
    String email,
    @NotNull(message = "Campo document não pode ser nulo")
    @NotBlank(message = "Campo document não pode ser vazio")
    @CPF(message = "CPF inválido")
    @CNPJ(message = "CNPJ inválido")
    String document,
    @Length(min = 1)
    Double wallet
) {
}