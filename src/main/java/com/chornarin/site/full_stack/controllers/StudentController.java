package com.chornarin.site.full_stack.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chornarin.site.full_stack.Response.Pagination;
import com.chornarin.site.full_stack.ServiceImp.StudentServiceImp;
import com.chornarin.site.full_stack.dto.StudentResponseDto;
import com.chornarin.site.full_stack.dto.requests.StudentRequest;
import com.chornarin.site.full_stack.models.Students;

import lombok.Data;

@Data
@RestController
@RequestMapping(StudentController.StudentUrl)
public class StudentController {
    public static final String StudentUrl = "/api/student";
    private final StudentServiceImp studentServiceImp;

    // @GetMapping
    // public ResponseEntity<List<StudentResponseDto>> getAll() {
    // return ResponseEntity.ok(studentServiceImp.getAll());
    // }

    // @GetMapping("/me")
    // public String getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
    //     User user = (User) userDetails;
    //     return "Hello, " + user.getUsername();
    // }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<StudentResponseDto>> getAll() {
    return ResponseEntity.ok(studentServiceImp.getAll());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudent(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy) {
        try {
            Pagination pagination = new Pagination();
            pagination.setPage(page);
            pagination.setSize(size);

            return ResponseEntity.ok(
                    studentServiceImp.getAllStudents(pagination));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Students> createStudent(@Validated @RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentServiceImp.createStudent(request));
    }

    @GetMapping("/email")
    public ResponseEntity<List<Students>> findByEmail(@RequestParam String email) {
        System.out.println("email" + email);
        return ResponseEntity.ok(studentServiceImp.getByEmail(email));
    }

    @GetMapping("/department")
    public ResponseEntity<List<Students>> findStudentsByDepartment(@RequestParam String departmentName) {
        return ResponseEntity.ok(studentServiceImp.getByDepartments(departmentName));
    }

    // get all with department
    // @GetMapping("/with-department")
    // public ResponseEntity<List<Students>> getAllwithdepartment() {
    // return ResponseEntity.ok(studentServiceImp.getAllwithdepartment());
    // }

}
