package com.example.payroll.web;

import com.example.payroll.domain.Employee;
import com.example.payroll.domain.EmployeeWithManager;
import com.example.payroll.exception.EmployeeNotFoundException;
import com.example.payroll.repository.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeRepresentationModelAssembler assembler;
    private final EmployeeWithManagerResourceAssembler employeeWithManagerResourceAssembler;

    EmployeeController(EmployeeRepository repository, EmployeeRepresentationModelAssembler assembler,
                       EmployeeWithManagerResourceAssembler employeeWithManagerResourceAssembler) {

        this.repository = repository;
        this.assembler = assembler;
        this.employeeWithManagerResourceAssembler = employeeWithManagerResourceAssembler;
    }

    /**
     * Look up all employees, and transform them into a REST collection resource using
     * {@link EmployeeRepresentationModelAssembler#toCollectionModel(Iterable)}. Then return them through Spring Web's
     * {@link ResponseEntity} fluent API.
     */
    @GetMapping("/employees")
    public ResponseEntity<CollectionModel<EntityModel<Employee>>> findAll() {

        return ResponseEntity.ok(assembler.toCollectionModel(repository.findAll()));

    }

    /**
     * Look up a single {@link Employee} and transform it into a REST resource using
     * {@link EmployeeRepresentationModelAssembler#toModel(Object)}. Then return it through Spring Web's
     * {@link ResponseEntity} fluent API.
     *
     * @param id
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<EntityModel<Employee>> findOne(@PathVariable long id) {

        return repository.findById(id) //
                .map(assembler::toModel) //
                .map(ResponseEntity::ok) //
                //.orElse(ResponseEntity.notFound().build());
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /**
     * Find an {@link Employee}'s {@linkManager} based upon employee id. Turn it into a context-based link.
     *
     * @param id
     * @return
     */
    @GetMapping("/managers/{id}/employees")
    public ResponseEntity<CollectionModel<EntityModel<Employee>>> findEmployees(@PathVariable long id) {

        return ResponseEntity.ok(assembler.toCollectionModel(repository.findByManagerId(id)));
    }

    @GetMapping("/employees/detailed")
    public ResponseEntity<CollectionModel<EntityModel<EmployeeWithManager>>> findAllDetailedEmployees() {

        return ResponseEntity.ok( //
                employeeWithManagerResourceAssembler.toCollectionModel( //
                        StreamSupport.stream(repository.findAll().spliterator(), false) //
                                .map(EmployeeWithManager::new) //
                                .collect(Collectors.toList())));
    }

    @GetMapping("/employees/{id}/detailed")
    public ResponseEntity<EntityModel<EmployeeWithManager>> findDetailedEmployee(@PathVariable Long id) {

        return repository.findById(id) //
                .map(EmployeeWithManager::new) //
                .map(employeeWithManagerResourceAssembler::toModel) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }
}
