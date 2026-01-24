package com.chornarin.site.full_stack.ServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chornarin.site.full_stack.dto.StudentRequestDto;
import com.chornarin.site.full_stack.dto.StudentResponseDto;
import com.chornarin.site.full_stack.mappers.StudentMapper;
import com.chornarin.site.full_stack.models.Departments;
import com.chornarin.site.full_stack.models.Students;
import com.chornarin.site.full_stack.repository.DepartmentRepository;
import com.chornarin.site.full_stack.repository.StudentRepository;
import com.chornarin.site.full_stack.services.StudentService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentMapper studentMapper;
    
    @Override
    public Students createStudent(StudentRequestDto studentDto) {
        // find department Id
        Optional<Departments> department = departmentRepository.findById(studentDto.getDepartmentId());
        if(department.isEmpty()){
            throw new RuntimeException("department not found");
        }
        // get department
        Departments departments = department.get();
        Students students = studentMapper.toEntity(studentDto, departments);
        return studentRepository.save(students);
    }

    @Override
    public List<Departments> findDepartmentById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findDepartmentById'");
    }

    @Override
    public List<StudentResponseDto> getAll() {
        List<Students> students = studentRepository.findAll();
        List<StudentResponseDto> mappers = studentMapper.toDto(students);
        return mappers;
    }
}
