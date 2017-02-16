package com.info.nowin.singleTableInheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author michlu
 * @sience 16.02.2017
 */
@Entity
@DiscriminatorValue(value = "Profesorek") // nazwa typu rekordu
public class Profesor extends Person {
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
