package com.example.payroll.database;

import com.example.payroll.domain.Employee;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.repository.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import com.example.payroll.domain.Manager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, ManagerRepository managerRepository) {
        return args -> {

            /*
             * Gather Gandalf's team
             */
            Manager gandalf = managerRepository.save(new Manager("Gandalf"));

            Employee frodo = employeeRepository.save(new Employee("Frodo", "ring bearer", gandalf));
            Employee bilbo = employeeRepository.save(new Employee("Bilbo", "burglar", gandalf));

            gandalf.setEmployees(Arrays.asList(frodo, bilbo));
            managerRepository.save(gandalf);

            /*
             * Put together Saruman's team
             */
            Manager saruman = managerRepository.save(new Manager("Saruman"));

            Employee sam = employeeRepository.save(new Employee("Sam", "gardener", saruman));

            saruman.setEmployees(Arrays.asList(sam));

            managerRepository.save(saruman);
        };
    }
}