package com.example.payroll.repository;

import com.example.payroll.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Greg Turnquist
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByManagerId(Long id);

}
