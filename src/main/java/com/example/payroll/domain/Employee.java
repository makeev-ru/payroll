package com.example.payroll.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Optional;

@Entity
public class Employee {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String position;
    private Double salary;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    @JsonIgnore
    @ManyToOne
    private Department department;


    public Employee(String name, String position, Department department, double salary) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
    }

    public Employee() {
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }
}
