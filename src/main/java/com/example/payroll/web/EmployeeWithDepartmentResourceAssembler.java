package com.example.payroll.web;

import com.example.payroll.domain.EmployeeWithDepartment;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class EmployeeWithDepartmentResourceAssembler implements SimpleRepresentationModelAssembler<EmployeeWithDepartment> {

    @Override
    public void addLinks(EntityModel<EmployeeWithDepartment> resource) {

        resource.add(
                linkTo(methodOn(EmployeeController.class).findDetailedEmployee(resource.getContent().getId())).withSelfRel());
        resource.add(linkTo(methodOn(EmployeeController.class).findOne(resource.getContent().getId())).withRel("summary"));
        resource.add(linkTo(methodOn(EmployeeController.class).findAllDetailedEmployees()).withRel("detailedEmployees"));
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<EmployeeWithDepartment>> resources) {

        resources.add(linkTo(methodOn(EmployeeController.class).findAllDetailedEmployees()).withSelfRel());
        resources.add(linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"));
        resources.add(linkTo(methodOn(DepartmentController.class).findAll()).withRel("managers"));
//		resources.add(linkTo(methodOn(RootController.class).root()).withRel("root"));
    }
}
