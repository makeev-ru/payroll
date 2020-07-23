package com.example.payroll.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

@Value
@JsonPropertyOrder({"id", "name", "position", "department", "salary"})
public class EmployeeWithDepartment {

    @JsonIgnore
    private Employee employee;

    public Long getId() {

        return this.employee.getId()
                .orElseThrow(() -> new RuntimeException("Couldn't find anything."));
    }

    public String getName() {
        return this.employee.getName();
    }

    public String getPosition() {
        return this.employee.getPosition();
    }

    public String getDepartment() {
        return this.employee.getDepartment().getName();
    }

    public Double getSalary() {
        return this.employee.getSalary();
    }

}
