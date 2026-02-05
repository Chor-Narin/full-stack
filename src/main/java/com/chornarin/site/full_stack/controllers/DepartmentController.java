// package com.chornarin.site.full_stack.controllers;

// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.chornarin.site.full_stack.ServiceImp.DepartmentServiceImp;
// import com.chornarin.site.full_stack.dto.DepartmentRequestDto;
// import com.chornarin.site.full_stack.models.Departments;

// import lombok.Data;

// @Data
// @RestController()
// @RequestMapping(DepartmentController.DepartmentUrl)
// public class DepartmentController {
//     public static final String  DepartmentUrl = "/department";
//     private final DepartmentServiceImp departmentServiceImp;

//     @PostMapping
//     public ResponseEntity<Departments> createDepartment(DepartmentRequestDto dto){
//         return ResponseEntity.ok(departmentServiceImp.create(dto));
//     }

//     @GetMapping
//     public ResponseEntity<List<Departments>> getDepartment(){
//         return ResponseEntity.ok(departmentServiceImp.getAll());
//     }

//     // get student with department
//     // @GetMapping("/students")
//     // public ResponseEntity<DepartmentRequestDto> getDepartmentWithStudent(@RequestParam Long id){
//     //     return ResponseEntity.ok(departmentServiceImp.getDepartmentWithStudents(id));
//     // }

//     @GetMapping("/all-students")
//     public ResponseEntity<List<DepartmentRequestDto>> getAllDepartment(){
//         return ResponseEntity.ok(departmentServiceImp.getAllDepartmentsWithStudents());
//     }

// }
