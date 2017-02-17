package com.info.nowin.tablePerClassInheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author michlu
 * @sience 17.02.2017
 */
@Entity
@Table(name = "Profesor2")
public class Profesor extends Person {
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
