package com.chornarin.site.full_stack.dto.responses;


public record AuthResponse(
    String token,
    String refreshToken,
    String type,
    long expiresIn,
    UserDto user
) {

}
