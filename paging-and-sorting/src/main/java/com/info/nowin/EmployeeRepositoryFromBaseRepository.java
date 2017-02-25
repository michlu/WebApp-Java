package com.info.nowin;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * MODYFIKACJE
 *
 * @author michlu
 * @sience 21.02.2017
 */
public interface EmployeeRepositoryFromBaseRepository extends EmployeeBaseRepository {
    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.salary = :newSalary")
    int setSalaryForAll(
            @Param("newSalary") BigDecimal newSalary);
}
