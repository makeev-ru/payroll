package com.example.payroll.domain;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Data
@Entity
public
class Employee {

    private @Id
    @GeneratedValue
    Long id;
    private String firstName;
    private String lastName;
    private String role;

    Employee() {
    }

    public Employee(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String wholeName) {

        String[] parts = wholeName.split(" ");
        this.firstName = parts[0];
        if (parts.length > 1) {
            this.lastName = StringUtils.arrayToDelimitedString(Arrays.copyOfRange(parts, 1, parts.length), " ");
        } else {
            this.lastName = "";
        }
    }
}
