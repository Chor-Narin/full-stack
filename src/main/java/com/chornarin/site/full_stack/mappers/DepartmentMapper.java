package com.chornarin.site.full_stack.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.chornarin.site.full_stack.dto.DepartmentRequestDto;
import com.chornarin.site.full_stack.models.Departments;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Departments toEntity(DepartmentRequestDto dto);

}
