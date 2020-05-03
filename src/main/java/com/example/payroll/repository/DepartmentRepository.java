package com.example.payroll.repository;

import com.example.payroll.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByEmployeesId(Long id);

    Optional<Department> findByName(String name);

}
