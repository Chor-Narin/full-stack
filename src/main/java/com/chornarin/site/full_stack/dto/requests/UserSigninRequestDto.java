package com.chornarin.site.full_stack.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSigninRequestDto {

    @NotBlank
    @Email
    private String username;
    @NotBlank
    private String password;

}
