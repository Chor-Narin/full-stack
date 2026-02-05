package com.chornarin.site.full_stack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chornarin.site.full_stack.models.Students;
import java.time.LocalDateTime;
import com.chornarin.site.full_stack.Enum.StudentStatusEnum;



@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {

    List<Students> findByCreatedAt(LocalDateTime createdAt);

    List<Students> findByEmail(String email);

    // get all
    List<Students> findAll();

     // count by status
    List<Students> findByDepartmentName(String departmentName);

    // create by student
    List<Students> findByStatus(StudentStatusEnum status);

    // get all with department
    @Query("SELECT s FROM Students s LEFT JOIN FETCH s.department")
    List<Students> findAllWithDepartment();


}
