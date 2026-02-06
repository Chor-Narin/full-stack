package com.chornarin.site.full_stack.dto.requests;

import com.chornarin.site.full_stack.Enum.StudentStatusEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank @Email String email,
    @NotNull StudentStatusEnum status,
    @NotNull Long department

) {

}
