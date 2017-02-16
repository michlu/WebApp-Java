package com.info.nowin.manyToMany;

import com.info.nowin.oneToManyBidirectional.Phone;

import javax.persistence.*;
import java.util.List;

/**
 * @author Michlu
 * @sience 2017-02-12
 */
@Entity
@Table(name = "Pracownicy8")
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

    @ManyToMany(mappedBy = "employees") // nazwa pola po przeciwnej stronie (w projects)
    List<Project> projects;

    public  Employee(){}// kazda mapowana klasa musi miec pusty konstruktor

    public Employee(String firsNtame, String lastName, double salary) {
        this.firsNtame = firsNtame;
        this.lastName = lastName;
        this.salary = salary;
    }

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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}