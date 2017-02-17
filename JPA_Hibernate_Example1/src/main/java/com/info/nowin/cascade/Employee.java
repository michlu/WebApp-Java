package com.info.nowin.cascade;

import javax.persistence.*;
import java.util.List;

/**
 * @author Michlu
 * @sience 2017-02-12
 */
@Entity
@Table(name = "Pracownicy9")
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

    @OneToMany(cascade = CascadeType.ALL) // polacznie kaskadowe
    @JoinColumn(name = "owner_id3")
    private List<Phone> phones;

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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}