package com.example.payroll.web;

import com.example.payroll.domain.Employee;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class EmployeeResourceAssembler extends SimpleIdentifiableRepresentationModelAssembler<Employee> {

    EmployeeResourceAssembler() {
        super(EmployeeController.class);
    }

    @Override
    public void addLinks(EntityModel<Employee> resource) {

        super.addLinks(resource);

        resource.getContent().getId() //
                .ifPresent(id -> { //
                    resource.add(linkTo(methodOn(DepartmentController.class).findDepartment(id)).withRel("department"));
                    resource.add(linkTo(methodOn(EmployeeController.class).findDetailedEmployee(id)).withRel("detailed"));
                });
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Employee>> resources) {

        super.addLinks(resources);

        resources.add(linkTo(methodOn(EmployeeController.class).findAllDetailedEmployees()).withRel("detailedEmployees"));
        resources.add(linkTo(methodOn(DepartmentController.class).findAll()).withRel("departments"));
//        resources.add(linkTo(methodOn(RootController.class).root()).withRel("root"));
    }
}