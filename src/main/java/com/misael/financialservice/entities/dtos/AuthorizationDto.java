package com.misael.financialservice.entities.dtos;

import com.misael.financialservice.entities.User;
import lombok.Builder;

@Builder
public record AuthorizationDto(
        User payer,
        Double value
) {
}
