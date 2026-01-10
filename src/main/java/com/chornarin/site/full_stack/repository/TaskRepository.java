package com.chornarin.site.full_stack.repository;
import org.springframework.scheduling.config.Task;
// Removed incorrect import
import org.springframework.stereotype.Repository;

import com.chornarin.site.full_stack.models.TaskModel;


import java.time.LocalDateTime;
import java.util.*;

@Repository
public class TaskRepository {

    private final Map<Long, TaskModel> tasks = new HashMap<>();

    public TaskRepository() {
        tasks.put(1L, new TaskModel(1L, "Learn Spring Boot", "Read Spring Boot guide", false, LocalDateTime.now()));
        tasks.put(2L, new TaskModel(2L, "Do Homework", "Complete math exercises", true, LocalDateTime.now()));
        tasks.put(3L, new TaskModel(3L, "Go Shopping", "Buy groceries", false, LocalDateTime.now()));
    }

    public List<TaskModel> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Optional<TaskModel> findById(Long id) {
       return Optional.ofNullable(tasks.get(id));
    }


    public TaskModel save(TaskModel task) {
        if (task.getId() == null) {
            long newId = tasks.keySet().stream().mapToLong(Long::longValue).max().orElse(0) + 1;
            task.setId(newId);
        }
        task.setCreatedAt(LocalDateTime.now());
        tasks.put(task.getId(), task);
        return task;
    }

    public String deleteById(Long id) {
        tasks.remove(id);
        return "Task delete successfully";
    }
}
