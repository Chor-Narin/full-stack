// package com.chornarin.site.full_stack.ServiceImp;

// import java.util.List;

// import org.springframework.stereotype.Service;
// import org.springframework.web.ErrorResponseException;

// import com.chornarin.site.full_stack.dto.DepartmentRequestDto;
// import com.chornarin.site.full_stack.models.Departments;
// import com.chornarin.site.full_stack.repository.DepartmentRepository;
// import com.chornarin.site.full_stack.services.DepartmentService;

// import lombok.AllArgsConstructor;
// import lombok.Data;

// @Service
// @Data
// @AllArgsConstructor
// public class DepartmentServiceImp implements DepartmentService {
//     private final DepartmentRepository pRepository;
//     // private final DepartmentMapper mapper;

//     @Override
//     public boolean existByName(String name) {

//         throw new UnsupportedOperationException("Unimplemented method 'existByName'");
//     }

//     @Override
//     public Departments create(DepartmentRequestDto departmentRequestDto) {
//         try {
//             Departments create = mapper.toEntity(departmentRequestDto);
//             return pRepository.save(create);
//         } catch (Exception e) {
//             throw new ErrorResponseException(null);
//         }

//     }

//     @Override
//     public List<Departments> getAll() {
//         return pRepository.findAll();

//     }

//     // public DepartmentRequestDto getDepartmentWithStudents(Long id) {
//     //     Departments department = pRepository.findByIdWithStudents(id)
//     //             .orElseThrow(() -> new RuntimeException("Department not found"));

//     //     return mapper.toDto(department);
//     // }

//     // public List<DepartmentRequestDto> getAllDepartmentsWithStudents(){
//     //     List<Departments> listDepartments = pRepository.findAll();
//     //     List<DepartmentRequestDto> dtoList = mapper.toDtoList(listDepartments);
//     //     return dtoList;
//     // }
// }
