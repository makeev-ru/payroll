package com.example.payroll.database;

import com.example.payroll.domain.Department;
import com.example.payroll.domain.Employee;
import com.example.payroll.repository.DepartmentRepository;
import com.example.payroll.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class LoadDatabase {

    private final EmployeeRepository repository;

    LoadDatabase(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        return args -> {

            //Testing team
            Department testing = departmentRepository.save(new Department("Testing"));

            Employee john = employeeRepository.save(new Employee("John Doo", "QA engineer", testing, 1500));
            Employee bob = employeeRepository.save(new Employee("Bob Boo", "QA Automation", testing, 1500));

            testing.setEmployees(Arrays.asList(john, bob));
            departmentRepository.save(testing);

            //Development team
            Department development = departmentRepository.save(new Department("Development"));

            Employee sam = employeeRepository.save(new Employee("Sam Boo", "Front-end developer", testing, 1500));

            development.setEmployees(Arrays.asList(sam));

            departmentRepository.save(development);
        };
    }

}