package com.example.payroll.service;

import com.example.payroll.domain.Department;
import com.example.payroll.domain.Employee;
import com.example.payroll.repository.DepartmentRepository;
import com.example.payroll.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@Slf4j
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee createNew(String name, String position, String department, Double salary)
            throws NoSuchElementException {
        return employeeRepository.save(new Employee(name, position, verifyDepartment(department), salary));
    }

    public Department verifyDepartment(String department) throws NoSuchElementException {
        return departmentRepository
                .findByName(department)
                .orElseThrow(() -> new NoSuchElementException("Department '" + department + "' does not exist"));
    }
}
