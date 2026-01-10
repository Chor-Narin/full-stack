// package com.chornarin.site.full_stack.services;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;
// import java.util.concurrent.atomic.AtomicLong;

// import org.springframework.stereotype.Service;

// import com.chornarin.site.full_stack.models.Task;
// import com.chornarin.site.full_stack.repository.TaskRepository;

// @Service
// public class TaskService {
  
//     private final TaskRepository taskRepository;
//     public TaskService(TaskRepository taskRepository) {
//         this.taskRepository = taskRepository;
//     }

//     private final Map<String, Task> tasks = new ConcurrentHashMap<>();
//     private final AtomicLong nextId = new AtomicLong(1L);

//     public List<Task> findAll(){
//         return taskRepository.findAll();
//     }

    
//     public Task create(String title, String description) {
//         Task task = new Task(nextId.getAndIncrement(), title, description, false, null);
//         tasks.put(String.valueOf(task.getId()), task);
//         return task;
//     }
// }


 package com.chornarin.site.full_stack.services;

import java.util.List;
import java.util.Optional;

import com.chornarin.site.full_stack.dto.TaskRequestDto;
import com.chornarin.site.full_stack.dto.TaskResponseDto;
import com.chornarin.site.full_stack.models.TaskModel;


public interface TaskService {

    // Get All
    List<TaskModel> getAlls();
    // TaskModel createTask(TaskModel task);

    // Create
    TaskResponseDto createTask (TaskRequestDto task);

    // Get By Id
    Optional<TaskResponseDto> getById(Long id);

    // Delete By Id
    String deleteById(Long id);

}