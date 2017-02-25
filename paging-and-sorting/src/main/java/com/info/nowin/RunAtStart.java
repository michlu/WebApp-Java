package com.info.nowin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;
    private final Logger logger = Logger.getLogger(RunAtStart.class);
    private final EmployeeRepositoryWithAnnotation employeeRepositoryWithAnnotation;
//    private final EmployeeBaseRepository employeeBaseRepository;
    private final EmployeeRepositoryFromBaseRepository employeeRepositoryFromBaseRepository;

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository,
                      EmployeeGenerator employeeGenerator,
                      EmployeeRepositoryWithAnnotation employeeRepositoryWithAnnotation,
//                      EmployeeBaseRepository employeeBaseRepository,
                      EmployeeRepositoryFromBaseRepository employeeRepositoryFromBaseRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
        this.employeeRepositoryWithAnnotation = employeeRepositoryWithAnnotation;
//        this.employeeBaseRepository = employeeBaseRepository;
        this.employeeRepositoryFromBaseRepository = employeeRepositoryFromBaseRepository;
    }

    @PostConstruct
    public void runAtStart() {
        generateManyEmployees();

//        STANDARDOWE METODY REPOZYTORIOW:

//        logger.info("UNSORTED");
//        printAll(findAllUnsorted());
//
//        logger.info("SORTED BY FIRST NAME");
//        printAll(getSortedByFirstName());
//
//        logger.info("SORTED BY FIRST AND LAST NAME");
//        printAll(getSortedByFirstAndLastName());
//
//        Page<Employee> page = employeeRepository.findAll(new PageRequest(2, 10));
//        logger.info("TOTAL NUMBER OF EMPLOYEES: " + page.getTotalElements());
//        logger.info("TOTAL NUMBER OF PAGES: " + page.getTotalPages());
//        logger.info("CURRENT PAGE NUMBER: " + page.getNumber());
//        logger.info("ELEMENTS ON PAGE:");
//        printAll(page.getContent());

        // WLASNE METODY REPOZYTORIOW

        logger.info("======================================CUSTOM REPOSITORIES======================================");
        logger.info("FIND: Dylan");
        printAll(employeeRepository.findByFirstNameIgnoreCase("Dylan"));
        logger.info("FIND: Smith - posegregowane od z do a");
        printAll(employeeRepository.findByLastNameOrderByFirstNameDesc("Smith"));
        logger.info("FIND: osoby zarabiajace pomiedzy 1000 - 2000");
        printAll(employeeRepository.findBySalaryBetween(new BigDecimal("1000.0"), new BigDecimal("2000.0")));

        logger.info("FIND: Michael: " + employeeRepository.findFirstByFirstName("Michael"));

        printAll(employeeRepository.findFirst3ByFirstName("Logan"));
        printAll(employeeRepository.findTop3ByFirstName("Logan"));

        logger.info("Number of Dylan Jones: " + employeeRepository.countByFirstNameAndLastNameIgnoreCase("Dylan", "Jones"));

        // strona z elementami
        Page<Employee> dylan = employeeRepository.findByFirstName("Dylan", new PageRequest(1,2));
        printAll(dylan.getContent());
        logger.info("Total numer of Dylans: " + dylan.getTotalElements());

        Stream<Employee> michael = employeeRepository.findTop5ByfirstName("Michael");
        List<String> collectMichael = michael.map(Employee::getLastName).collect(Collectors.toList());
        logger.info("Michaels last names: " + collectMichael);

        employeeRepository.findFirstByFirstNameIgnoreCase("Jack").thenAccept(jack -> {
            logger.info("Jack " + jack);
        });

        // ========== MODYFIKACJE
        int numberOfUpdates = employeeRepositoryFromBaseRepository.setSalaryForAll(new BigDecimal(5000.0)); // kazdy bedzie zarabial 5000
        logger.info("UPDATE: " + numberOfUpdates + " ENTITES");

    }

    private List<Employee> findAllUnsorted() {
        return employeeRepository.findAll();
    }

    private void generateManyEmployees() {
        for (int i = 0; i < 100; i++) {
            employeeRepository.save(
                    employeeGenerator.generate());
        }
    }

    private List<Employee> getSortedByFirstAndLastName() {
        return employeeRepository.findAll(
                new Sort(
                        new Sort.Order(
                                Sort.Direction.ASC, "firstName"
                        ),
                        new Sort.Order(
                                Sort.Direction.ASC, "lastName"
                        )
                ));
    }

    private List<Employee> getSortedByFirstName() {
        return employeeRepository.findAll(
                new Sort(
                        Sort.Direction.ASC,
                        "firstName"
                ));
    }

    private void printAll(List<Employee> allUnsorted) {
        allUnsorted.forEach(logger::info);
    }
}
