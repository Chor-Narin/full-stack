package com.chornarin.site.full_stack.data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.chornarin.site.full_stack.models.TaskModel;

public class TaskData {

    private Map<String, TaskModel> tasks = new HashMap<>();

    // Constructor
    public TaskData() {
        // Adding sample data
        tasks.put("1", new TaskModel(1L, "Task 1", "Description for Task 1", false, LocalDateTime.now()));
        tasks.put("2", new TaskModel(2L, "Task 2", "Description for Task 2", true, LocalDateTime.now()));
    }

    // Getter
    public Map<String, TaskModel> getTasks() {
        return tasks;
    }

    // Optional: Add a method to get a task by ID
    public TaskModel getTaskById(String id) {
        return tasks.get(id);
    }

    // Optional: Add a method to add new tasks
    public void addTask(String id, TaskModel task) {
        tasks.put(id, task);
    }
}
