package com.chornarin.site.full_stack.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.chornarin.site.full_stack.Response.Pagination;
import com.chornarin.site.full_stack.Response.PaginationResponse;
import com.chornarin.site.full_stack.config.AuditConfig;
import com.chornarin.site.full_stack.dto.StudentRequestDto;
import com.chornarin.site.full_stack.dto.StudentResponseDto;
import com.chornarin.site.full_stack.dto.requests.StudentRequest;
import com.chornarin.site.full_stack.mappers.StudentMapper;
import com.chornarin.site.full_stack.models.Departments;
import com.chornarin.site.full_stack.models.Students;
import com.chornarin.site.full_stack.repository.DepartmentRepository;
import com.chornarin.site.full_stack.repository.StudentRepository;
import com.chornarin.site.full_stack.services.StudentService;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
@Data
public class StudentServiceImp implements StudentService {

    private final AuditConfig auditConfig;
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentMapper studentMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Students createStudent(StudentRequest request) {
        // find department Id
        Optional<Departments> department = departmentRepository.findById(request.department());
        if (department.isEmpty()) {
            throw new RuntimeException("department not found");
        }
        // get department
        Departments departments = department.get();
        Students students = studentMapper.toEntity(request, departments);
        return studentRepository.save(students);
    }

    @Override
    public List<Departments> findDepartmentById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findDepartmentById'");
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public List<StudentResponseDto> getAll() {
        List<Students> students = studentRepository.findAll();
        List<StudentResponseDto> mappers = studentMapper.toDto(students);
        return mappers;
    }

    // get all students
    public PaginationResponse<StudentResponseDto> getAllStudents(Pagination pagination) {

        Pageable pageable = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                Sort.by("id").ascending());

        Page<Students> pages = studentRepository.findAll(pageable);

        // update pagination info
        pagination.setTotalPages(pages.getTotalPages());
        pagination.setTotalElements(pages.getTotalElements());
        pagination.setHasNext(pages.hasNext());
        pagination.setHasPrevious(pages.hasPrevious());

        return new PaginationResponse<>(studentMapper.toDto(pages.getContent()), pagination);
    }

    @Override
    public List<Students> getByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // get by Department
    public List<Students> getByDepartments(String departmentName) {
        return studentRepository.findByDepartmentName(departmentName);
    }

    // get all with department
    public List<Students> getAllWithDepartment() {
        return studentRepository.findAllWithDepartment();
    }


}
