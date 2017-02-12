package com.info.nowin.embeddedClass;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable // klasa osadzajaca sie/ dajaca sie osadzic
public class Adress {
    @Column(name = "miejscowosc")
    private String locality;
    @Column(name = "kodPocztowy")
    private String zipCode;
    @Column(name = "ulica")
    private String street;
    @Column(name = "numerDomu")
    private int streetNumber;


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