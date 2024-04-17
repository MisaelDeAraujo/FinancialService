package com.misael.financialservice.entities.dtos;

import lombok.Builder;

/**
 * TransactionDto
 */
@Builder
public record TransactionDto(
    Double value,
    Integer payer,
    Integer payee
) {
}