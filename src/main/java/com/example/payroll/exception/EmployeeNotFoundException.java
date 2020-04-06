package com.example.payroll.exception;

import java.util.Optional;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(long id) {
        super("Could not find employee " + id);
    }
}
