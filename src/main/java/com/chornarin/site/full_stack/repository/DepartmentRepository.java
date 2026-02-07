package com.chornarin.site.full_stack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chornarin.site.full_stack.models.Departments;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Long> {

    @Query("SELECT d FROM Departments d LEFT JOIN FETCH d.students WHERE d.id = :id")
    Optional<Departments> findByIdWithStudents(@Param("id") Long id);

}
