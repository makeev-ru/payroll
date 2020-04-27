package com.example.payroll.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Optional;

@Data
@Entity
public
class Employee {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String position;
    private Double salary;

    @JsonIgnore
    @OneToOne
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
