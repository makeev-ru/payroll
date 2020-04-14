package com.example.payroll.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public
class Employee {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String position;
    private String department;
    private Double salary;


    public Employee(String name, String position, String department, double salary) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
    }

    public Employee() {
    }
}
