package com.info.nowin.tablePerClassInheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author michlu
 * @sience 17.02.2017
 */

@Entity
@Table(name = "Student1")
public class Student extends Person {

    private double avarageGrade; // srednia ocen

    public double getAvarageGrade() {
        return avarageGrade;
    }

    public void setAvarageGrade(double avarageGrade) {
        this.avarageGrade = avarageGrade;
    }
}
