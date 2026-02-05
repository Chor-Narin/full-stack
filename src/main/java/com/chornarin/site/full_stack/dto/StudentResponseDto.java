package com.chornarin.site.full_stack.dto;


import lombok.Data;

@Data
public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private DepartmentRequestDto departmentRequestDto;
}
