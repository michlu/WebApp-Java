package com.info.nowin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Employee.class, idClass =  Long.class)
public interface EmployeeRepositoryWithAnnotation {
    Employee save(Employee employee);
    List<Employee> findAll();
}
