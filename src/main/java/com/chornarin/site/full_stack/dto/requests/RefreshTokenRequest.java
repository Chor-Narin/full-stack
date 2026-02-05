package com.chornarin.site.full_stack.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
    @NotBlank String refreshToken
) {

}
