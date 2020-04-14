package com.example.payroll.repository;

import com.example.payroll.domain.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Department findByEmployeesId(Long id);
}
