package com.chornarin.site.full_stack.dto.responses;

import com.chornarin.site.full_stack.enums.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSigninResponseDto {

    private boolean success;
    private Integer status;
    private String email;
    private String message;
    private RoleEnum role;
    private String access_token;
    private String refresh_token;

}
