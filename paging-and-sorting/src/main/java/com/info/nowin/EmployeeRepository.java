package com.info.nowin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // pobranie pracownikow po imieniu
    List<Employee> findByFirstNameIgnoreCase(String firstName);

    List<Employee> findByLastNameOrderByFirstNameDesc(String lastName);

    // wszsycy pracownicy co maja pensje w przedziale
    List<Employee> findBySalaryBetween(BigDecimal salary1, BigDecimal salary2);

    Employee findFirstByFirstName(String firstName);

    // dziala tak samo:
    List<Employee> findTop3ByFirstName(String firstNamew);
    List<Employee> findFirst3ByFirstName(String firstNamew);

    int countByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);

    // sortowanie i stronicowanie
    Page<Employee> findByFirstName(String firstName, Pageable pageable);

    Stream<Employee> findTop5ByfirstName(String firstName);

    // asynchronicznosc
    @Async
    Future<List<Employee>> findDistinctByFirstName(String firstName);

    @Async
    CompletableFuture<Employee> findFirstByFirstNameIgnoreCase(String firstName);

    @Async
    ListenableFuture<Employee> findFirstByLastName(String lastName);
}
