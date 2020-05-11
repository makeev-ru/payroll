package com.example.payroll.service;

import com.example.payroll.domain.Department;
import com.example.payroll.domain.Employee;
import com.example.payroll.repository.DepartmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@DisplayName("EmployeeService Integration tests")
public class EmployeeServiceIntegrationTest {

    private static final String NAME = "John";
    private static final String POSITION = "Java Developer";
    private static final String DEPARTMENT = "Development";
    private static final Double SALARY = 12345.0;

    private static final String NONEXISTENT_DEPARTMENT = "Management";
    private static final String NONEXISTENT_DEPARTMENT_EXCEPTION = "Department '" + NONEXISTENT_DEPARTMENT + "' does not exist";

    @Autowired
    private EmployeeService service;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("Create new Employee")
    public void createNew() {
        Employee newEmployee = service.createNew(NAME, POSITION, DEPARTMENT, SALARY);

        assertEquals(NAME, newEmployee.getName());
        assertEquals(POSITION, newEmployee.getPosition());
        assertEquals(DEPARTMENT, newEmployee.getDepartment().getName());
        assertEquals(SALARY, newEmployee.getSalary());
    }

    @Test
    @DisplayName("Create new Employee: Department exception")
    public void createNewEmployeeDepartmentException() {
        Throwable thrown = assertThrows(NoSuchElementException.class, () -> service.createNew(NAME, POSITION, NONEXISTENT_DEPARTMENT, SALARY));
        assertThat(thrown.getMessage(), is(equalTo(NONEXISTENT_DEPARTMENT_EXCEPTION)));
    }

    @Test
    @DisplayName("Verify Department test")
    public void verifyDepartment() {
        String departmentName = "newDepartment";
        Department newDepartment = departmentRepository.save(new Department(departmentName));
        assertEquals(newDepartment, service.verifyDepartment(departmentName));
    }

    @Test
    @DisplayName("Non existent Department exception")
    public void verifyDepartmentException() {
        Throwable thrown = assertThrows(NoSuchElementException.class, () -> service.verifyDepartment(NONEXISTENT_DEPARTMENT));
        assertThat(thrown.getMessage(), is(equalTo(NONEXISTENT_DEPARTMENT_EXCEPTION)));
    }
}