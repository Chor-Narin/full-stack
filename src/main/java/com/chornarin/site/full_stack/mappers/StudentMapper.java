package com.chornarin.site.full_stack.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.chornarin.site.full_stack.dto.StudentRequestDto;
import com.chornarin.site.full_stack.dto.StudentResponseDto;
import com.chornarin.site.full_stack.models.Departments;
import com.chornarin.site.full_stack.models.Students;


@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "department" , source = "department")
    Students toEntity(StudentRequestDto dto, Departments department);

    List<StudentResponseDto> toDto(List<Students> students);

}
