package com.info.nowin.joinedInheritance;

import javax.persistence.*;

/**
 * @author michlu
 * @sience 16.02.2017
 */
@Entity
@Table(name = "Person2")
@Inheritance(strategy = InheritanceType.JOINED) // osobne tabele
@DiscriminatorColumn(name = "typ_rekordu") // nazwa DTYPE
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
