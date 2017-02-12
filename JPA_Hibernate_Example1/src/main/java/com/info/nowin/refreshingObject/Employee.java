package com.info.nowin.refreshingObject;

import javax.persistence.*;

@Entity
@Table(name = "Pracownicy5")
public class Employee {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "imie")
    private String firsNtame;
    @Column(name = "nazwisko")
    private String lastName;
    @Column(name = "pensja")
    private double salary;

    @Column(name = "podatek") // tylko geter
    private double tax;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirsNtame() {
        return firsNtame;
    }

    public void setFirsNtame(String firsNtame) {
        this.firsNtame = firsNtame;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getTax() {
        return tax;
    }
}
