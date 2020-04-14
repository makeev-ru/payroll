package com.example.payroll.web;

import com.example.payroll.domain.Department;
import com.example.payroll.repository.DepartmentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DepartmentController {

    private final DepartmentRepository repository;
    private final DepartmentRepresentationModelAssembler assembler;

    DepartmentController(DepartmentRepository repository, DepartmentRepresentationModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/departments")
    ResponseEntity<CollectionModel<EntityModel<Department>>> findAll() {

        return ResponseEntity.ok( //
                assembler.toCollectionModel(repository.findAll()));

    }

    @GetMapping("/departments/{id}")
    ResponseEntity<EntityModel<Department>> findOne(@PathVariable long id) {

        return repository.findById(id) //
                .map(assembler::toModel) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employees/{id}/department")
    ResponseEntity<EntityModel<Department>> findDepartment(@PathVariable long id) {

        return ResponseEntity.ok( //
                assembler.toModel(repository.findByEmployeesId(id)));
    }
}
