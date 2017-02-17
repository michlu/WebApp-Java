package com.info.nowin.joinedInheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author michlu
 * @sience 16.02.2017
 */

@Entity
public class Student extends Person {

    private double avarageGrade; // srednia ocen

    public double getAvarageGrade() {
        return avarageGrade;
    }

    public void setAvarageGrade(double avarageGrade) {
        this.avarageGrade = avarageGrade;
    }
}
