package com.chornarin.site.full_stack.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chornarin.site.full_stack.ServiceImp.StudentServiceImp;
import com.chornarin.site.full_stack.dto.StudentRequestDto;
import com.chornarin.site.full_stack.dto.StudentResponseDto;
import com.chornarin.site.full_stack.models.Students;

import lombok.Data;

@Data
@RestController
@RequestMapping(StudentController.StudentUrl)
public class StudentController {
    public static final String StudentUrl = "/student";
    private final StudentServiceImp studentServiceImp;


    @GetMapping
    public ResponseEntity<List<StudentResponseDto >> getAll(){
        return ResponseEntity.ok(studentServiceImp.getAll());
    }

    @PostMapping
    public ResponseEntity<Students> createStudent(StudentRequestDto dto){
        return ResponseEntity.ok(studentServiceImp.createStudent(dto));
    }
}
