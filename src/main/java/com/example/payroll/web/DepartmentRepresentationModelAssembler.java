package com.example.payroll.web;

import com.example.payroll.domain.Department;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class DepartmentRepresentationModelAssembler implements SimpleRepresentationModelAssembler<Department> {

    public DepartmentRepresentationModelAssembler() {
    }

    @Override
    public void addLinks(EntityModel<Department> resource) {
        resource.getContent().getId() //
                .ifPresent(id -> { //
                    resource.add(linkTo(methodOn(EmployeeController.class).findEmployees(id)).withRel("employees"));
                });
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Department>> resources) {
        resources.add(linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"));
        resources.add(linkTo(methodOn(EmployeeController.class).findAllDetailedEmployees()).withRel("detailedEmployees"));
    }
}
