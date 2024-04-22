package com.misael.financialservice.entities.dtos;

import lombok.Builder;

/**
 * NotificationDto
 */
@Builder
public record NotificationDto(
    String email,
    String message
) {
}