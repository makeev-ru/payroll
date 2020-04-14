package com.example.payroll.database;

import com.example.payroll.domain.Employee;
import com.example.payroll.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class LoadDatabase {

    private final EmployeeRepository repository;

    LoadDatabase(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Bean
    CommandLineRunner loadEmployees() {

        return args -> {
            repository.save(new Employee("Frodo Baggins", "Developer", "Financial", 1500));
            repository.save(new Employee("Bilbo Baggins", "Tester", "Travel", 2000));
        };
    }

}