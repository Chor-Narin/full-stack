package com.chornarin.site.full_stack.mappers;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.chornarin.site.full_stack.dto.TaskRequestDto;
import com.chornarin.site.full_stack.dto.TaskResponseDto;
import com.chornarin.site.full_stack.models.TaskModel;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    // Request DTO -> Entity
    @Mapping(target="id",ignore= true)
    @Mapping(target = "completed", constant = "false")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    TaskModel toEntity(TaskRequestDto dto);

    //Response Entity to DTO
    TaskResponseDto toDto(TaskModel taskModel);

    // Get By Id
    TaskResponseDto getById(TaskModel taskModel);
    
}