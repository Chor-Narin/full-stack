package com.chornarin.site.full_stack.dto;

import com.chornarin.site.full_stack.Enum.StudentStatusEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @NotBlank(message =  "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull
    private StudentStatusEnum status;
    private Long departmentId;

}
