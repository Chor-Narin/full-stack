package com.chornarin.site.full_stack.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupResultDto {

    @NotBlank(message = "username required")
    private String username;
    @NotBlank(message = "password required")
    private String password;
    @NotBlank(message = "email required")
    private String email;

}
