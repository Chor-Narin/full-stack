package com.chornarin.site.full_stack.dto.responses;



public record UserDto(
    Long id,
    String email,
    String username,
    String role
) {

}
