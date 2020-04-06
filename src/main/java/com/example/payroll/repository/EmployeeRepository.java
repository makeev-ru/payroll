package com.example.payroll.repository;

import com.example.payroll.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Greg Turnquist
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
