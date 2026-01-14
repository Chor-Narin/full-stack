package com.chornarin.site.full_stack.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chornarin.site.full_stack.dto.TaskRequestDto;
import com.chornarin.site.full_stack.dto.TaskResponseDto;
import com.chornarin.site.full_stack.iml.TaskServiceImp;
import com.chornarin.site.full_stack.models.TaskModel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping(TaskController.ROOT_URL)
public class TaskController {

    public static final String ROOT_URL = "/api/tasks";

    private final TaskServiceImp takeserviceImp;

    @GetMapping
    public List<TaskModel> getAllTasks() {
        return takeserviceImp.getAlls();
    }

    @GetMapping("/{id}")
    public Optional<TaskResponseDto> getTaskById(Long id){
        return takeserviceImp.getById(id);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask( @Valid TaskRequestDto request) {
        TaskResponseDto task = takeserviceImp.createTask(request);
        URI location = URI.create("/api/tasks/" + task.getId());
        return ResponseEntity.created(location).body(task);
    }

    @DeleteMapping("/{id}")
    public String deleteTaskById(Long id){
        return takeserviceImp.deleteById(id);
    }
    
}
