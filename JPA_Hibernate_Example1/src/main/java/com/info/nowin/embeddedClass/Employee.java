package com.info.nowin.embeddedClass;

import javax.persistence.*;

@Entity
@Table(name = "Pracownicy2")
public class Employee {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "imie", nullable = false, length = 10)
    private String firsNtame;
    @Column(name = "nazwisko", columnDefinition = "VARCHAR(20) NOT NULL")
    private String lastName;
    @Column(name = "pensja")
    private double salary;

    /**
     * Klasa osadzana w tabeli Employee/Pracownicy2
     */
    @Embedded
    private Adress adress;

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

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
