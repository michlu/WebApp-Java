package com.info.nowin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * @author michlu
 * @sience 18.02.2017
 */
@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void runAtStart(){
        Employee employee1 = new Employee();
        employee1.setFirstName("Jacek");
        employee1.setLastName("Szymanski");
        employee1.setSalary(new BigDecimal(3000.0));

        employeeRepository.save(employee1); // zapisujemy do bazy

        Iterable<Employee> jacki = employeeRepository.findByFirstName("Jacek"); // metoda zwraca liste pracownikow
        Employee pierwszyJacek = jacki.iterator().next();
        System.out.println(pierwszyJacek);
    }
}
