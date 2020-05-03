package com.example.payroll.web;

import com.example.payroll.domain.Employee;
import com.example.payroll.domain.EmployeeWithDepartment;
import com.example.payroll.exception.EmployeeNotFoundException;
import com.example.payroll.repository.EmployeeRepository;
import com.example.payroll.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Slf4j
class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeResourceAssembler assembler;
    private final EmployeeWithDepartmentResourceAssembler employeeWithDepartmentResourceAssembler;
    private final EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeRepository repository, EmployeeResourceAssembler assembler, EmployeeWithDepartmentResourceAssembler employeeWithDepartmentResourceAssembler, EmployeeService employeeService) {

        this.repository = repository;
        this.assembler = assembler;
        this.employeeWithDepartmentResourceAssembler = employeeWithDepartmentResourceAssembler;
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<CollectionModel<EntityModel<Employee>>> findAll() {

        return ResponseEntity.ok(assembler.toCollectionModel(repository.findAll()));

    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EntityModel<Employee>> findOne(@PathVariable long id) {

        return repository.findById(id) //
                .map(assembler::toModel) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/employees")
    void newEmployee(@RequestBody EmployeeDto newEmployee) {
        employeeService.createNew(
                newEmployee.getName(),
                newEmployee.getPosition(),
                newEmployee.getDepartment(),
                newEmployee.getSalary());
    }

    @GetMapping("/departments/{id}/employees")
    public ResponseEntity<CollectionModel<EntityModel<Employee>>> findEmployees(@PathVariable long id) {

        return ResponseEntity.ok(assembler.toCollectionModel(repository.findByDepartmentId(id)));
    }

    @GetMapping("/employees/detailed")
    public ResponseEntity<CollectionModel<EntityModel<EmployeeWithDepartment>>> findAllDetailedEmployees() {

        return ResponseEntity.ok( //
                employeeWithDepartmentResourceAssembler.toCollectionModel( //
                        StreamSupport.stream(repository.findAll().spliterator(), false) //
                                .map(EmployeeWithDepartment::new) //
                                .collect(Collectors.toList())));
    }

    @GetMapping("/employees/{id}/detailed")
    public ResponseEntity<EntityModel<EmployeeWithDepartment>> findDetailedEmployee(@PathVariable Long id) {

        return repository.findById(id) //
                .map(EmployeeWithDepartment::new) //
                .map(employeeWithDepartmentResourceAssembler::toModel) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employees/{id}/salary")
    public AbstractMap.SimpleEntry<String, Double> getSalary(@PathVariable Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return new AbstractMap.SimpleEntry<>("salary", employee.getSalary());
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setPosition(newEmployee.getPosition());
                    employee.setSalary(newEmployee.getSalary());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
