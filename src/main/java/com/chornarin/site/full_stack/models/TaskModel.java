package com.chornarin.site.full_stack.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;

}