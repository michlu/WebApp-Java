package com.info.nowin;

import org.springframework.data.repository.CrudRepository;

/**
 * @author michlu
 * @sience 18.02.2017
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Iterable<Employee> findByFirstName(String firstName);
}
