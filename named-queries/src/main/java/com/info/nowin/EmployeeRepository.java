package com.info.nowin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllWithSalariesBetweenSomeValues(BigDecimal from, BigDecimal to);

    @Query(value = "SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(em.salary) FROM Employee em)")
    List<Employee> findGuyWithHighestsalary();

    // ogranicz do jednego wyniku
    @Query(value = "SELECT * FROM employee WHERE salary = (SELECT MAX(salary) FROM employee) LIMIT 1",
            nativeQuery = true)
    Employee findOnlyOneguyWithHighestSalary();

    // natywnie

    @Query(value = "SELECT * FROM employee WHERE salary BETWEEN :from AND :to",
            nativeQuery = true)
    List<Employee> findNativelyWithSalaryBetween(
            @Param("from") BigDecimal from,
            @Param("to") BigDecimal two
    );

}
