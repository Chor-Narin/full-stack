package com.chornarin.site.full_stack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chornarin.site.full_stack.models.Departments;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Long> {

}
