package com.chornarin.site.full_stack.dto.requests;

import com.chornarin.site.full_stack.enums.RoleEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

public record RegisterRequest(
    @NotBlank String username,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 4) String password,
    @NonNull  RoleEnum role
) {

}
