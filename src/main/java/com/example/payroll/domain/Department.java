package com.example.payroll.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonIgnore //
    @OneToMany(mappedBy = "department") //
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }
}
