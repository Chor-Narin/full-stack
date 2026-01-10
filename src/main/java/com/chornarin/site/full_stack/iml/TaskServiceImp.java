package com.chornarin.site.full_stack.iml;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.chornarin.site.full_stack.dto.TaskRequestDto;
import com.chornarin.site.full_stack.dto.TaskResponseDto;
import com.chornarin.site.full_stack.mappers.TaskMapper;
import com.chornarin.site.full_stack.models.TaskModel;
import com.chornarin.site.full_stack.repository.TaskRepository;
import com.chornarin.site.full_stack.services.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {
    private final TaskRepository taskRepository;
    private final AtomicLong nextId = new AtomicLong(1);
    private final TaskMapper    taskMapper;


    @Override
    public List<TaskModel> getAlls() {
        return taskRepository.findAll();
    }


    // // SIMPLE GET
    // @Override
    // public TaskModel createTask(TaskModel task) {
    //     TaskModel task1 = new TaskModel(nextId.getAndIncrement(), task.getTitle(), task.getDescription(), false, LocalDateTime.now());
    //     taskRepository.save(task1);
    //     return task1;
    // }

    @Override
    public TaskResponseDto createTask(TaskRequestDto task) {
        TaskModel taskmodel = taskMapper.toEntity(task);
        TaskModel saved = taskRepository.save(taskmodel);
        return taskMapper.toDto(saved);
    }


    @Override
    public Optional<TaskResponseDto> getById(Long id) {
        return taskRepository.findById(id).map(taskMapper::getById);
    }


    @Override
    public String deleteById(Long id) {
        return taskRepository.deleteById(id);
    }

}
