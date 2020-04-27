package com.example.payroll.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue
    private Long id;
    @GeneratedValue
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

    @Override
    public String toString() {
        return name;
    }
}
