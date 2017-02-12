package com.info.nowin.oneToOne;

import javax.persistence.*;

@Entity
@Table(name = "Pracownicy3")
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
     * Relacja jeden do jedenego
     */
    @OneToOne
    @JoinColumn(name = "adressId") // zdefiniowanie kolumny z kluczem obcym i nadanie nazwy
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
