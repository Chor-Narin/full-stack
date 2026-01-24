package com.chornarin.site.full_stack.dto;

import com.chornarin.site.full_stack.Enum.StudentStatusEnum;
import lombok.Data;

@Data
public class StudentRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private StudentStatusEnum Status;
    private Long departmentId; 

}
