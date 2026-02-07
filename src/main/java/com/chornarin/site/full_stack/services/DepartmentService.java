package com.chornarin.site.full_stack.services;

import java.util.List;

import com.chornarin.site.full_stack.dto.DepartmentRequestDto;
import com.chornarin.site.full_stack.models.Departments;

public interface DepartmentService {

    boolean existByName(String name);
    List<Departments>  getAll();
    Departments create(DepartmentRequestDto departmentRequestDto);

}
