package com.example.payroll.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
public class EmployeeDto extends RepresentationModel<EmployeeDto> {

    private String name;
    private String position;
    private Double salary;
    private String department;


    public EmployeeDto(String name, String position, String department, double salary) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
    }
}
