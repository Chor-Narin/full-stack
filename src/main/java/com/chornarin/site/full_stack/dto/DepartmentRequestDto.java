package com.chornarin.site.full_stack.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDto {

    private Long id;
    private String name;
    private List<StudentRequestDto> students;

}
