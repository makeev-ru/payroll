package com.example.payroll.web;

import com.example.payroll.domain.Department;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
class DepartmentRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Department> {

    DepartmentRepresentationModelAssembler() {
        super(DepartmentController.class);
    }

    @Override
    public void addLinks(EntityModel<Department> resource) {
        super.addLinks(resource);

        resource.getContent().getId() //
                .ifPresent(id -> { //
                    // Add custom link to find all managed employees
                    resource.add(linkTo(methodOn(EmployeeController.class).findEmployees(id)).withRel("employees"));
                });
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Department>> resources) {

        super.addLinks(resources);

        resources.add(linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"));
        resources.add(linkTo(methodOn(EmployeeController.class).findAllDetailedEmployees()).withRel("detailedEmployees"));
//		resources.add(linkTo(methodOn(RootController.class).root()).withRel("root"));
    }
}
