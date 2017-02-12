package com.info.nowin.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Pracownicy")
// dwie tabele z jednej encji
@SecondaryTable(name = "Adresy", pkJoinColumns = @PrimaryKeyJoinColumn(name = "employeeId")) // zmiana id drugiej kolumny powiazanej
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

    // przypisanie kolumn do odpowiednij tablicy
    @Column(table = "Adresy", name = "miejscowosc")
    private String locality;
    @Column(table = "Adresy", name = "kodPocztowy")
    private String zipCode;
    @Column(table = "Adresy", name = "ulica")
    private String street;
    @Column(table = "Adresy", name = "numerDomu")
    private int streetNumber;

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

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
}
