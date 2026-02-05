package com.chornarin.site.full_stack.services;

import java.util.List;

import com.chornarin.site.full_stack.dto.StudentRequestDto;
import com.chornarin.site.full_stack.dto.StudentResponseDto;
import com.chornarin.site.full_stack.models.Departments;
import com.chornarin.site.full_stack.models.Students;

public interface StudentService  {

    // get Student
    List<StudentResponseDto> getAll();

    // find department
    List<Departments> findDepartmentById(Long id);

    // create
    Students createStudent(StudentRequestDto studentDto);

    //get by email
    List<Students> getByEmail(String email);



}
