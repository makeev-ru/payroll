package com.example.payroll.service;

import com.example.payroll.domain.Department;
import com.example.payroll.domain.Employee;
import com.example.payroll.repository.DepartmentRepository;
import com.example.payroll.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@DisplayName("EmployeeService Unit tests")
class EmployeeServiceTest {

    private static final String NAME = "Bob";
    private static final String POSITION = "QA";
    private static final String DEPARTMENT = "Testing";
    private static final Double SALARY = 11122.0;
    private static final Long DEPARTMENT_ID = 1L;
    private static final Long EMPLOYEE_ID = 2L;

    @Mock
    private EmployeeRepository employeeRepositoryMock;
    @Mock
    private DepartmentRepository departmentRepositoryMock;

    @InjectMocks
    private EmployeeService service;

    @Mock
    private Employee employeeMock;
    @Mock
    private Department departmentMock;

    @Before
    public void setupReturnValuesOfMockMethods() {
        when(employeeRepositoryMock.findById(EMPLOYEE_ID)).thenReturn(Optional.of(employeeMock));
        when(employeeMock.getId()).thenReturn(Optional.of(EMPLOYEE_ID));
        when(departmentRepositoryMock.findByEmployeesId(EMPLOYEE_ID)).thenReturn(departmentMock);
        when(departmentRepositoryMock.findByName(DEPARTMENT)).thenReturn(Optional.of(departmentMock));
        when(employeeRepositoryMock.findByDepartmentId(DEPARTMENT_ID))
                .thenReturn(Collections.singletonList(employeeMock));
    }

    /** Verifies the invocation of dependencies and verifies the captured parameter values. */
    @Test
    void createNew() {
        // prepare to capture a Employee Object
        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
        when(departmentRepositoryMock.findByName(DEPARTMENT)).thenReturn(Optional.of(departmentMock));

        // invoke createNew
        service.createNew(NAME, POSITION, DEPARTMENT, SALARY);

        // verify employeeRepository.save invoked once and capture the Employee Object
        verify(employeeRepositoryMock).save(employeeCaptor.capture());

        // verify the attributes of the Employee Object
        assertThat(employeeCaptor.getValue().getName(), is(NAME));
        assertThat(employeeCaptor.getValue().getPosition(), is(POSITION));
        assertThat(employeeCaptor.getValue().getDepartment(), is(departmentMock));
        assertThat(employeeCaptor.getValue().getSalary(), is(SALARY));
    }
}