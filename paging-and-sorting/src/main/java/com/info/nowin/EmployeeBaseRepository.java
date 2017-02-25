package com.info.nowin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author michlu
 * @sience 21.02.2017
 */
@NoRepositoryBean // nie powstanie jego instancja
public interface EmployeeBaseRepository extends JpaRepository<Employee, Long> {
}
