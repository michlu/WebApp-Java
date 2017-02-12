package com.info.nowin.oneToOne;

import javax.persistence.*;

@Entity
public class Adress {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "miejscowosc")
    private String locality;
    @Column(name = "kodPocztowy")
    private String zipCode;
    @Column(name = "ulica")
    private String street;
    @Column(name = "numerDomu")
    private int streetNumber;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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