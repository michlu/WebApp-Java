package com.info.nowin.tablePerClassInheritance;

import javax.persistence.*;

/**
 * @author michlu
 * @sience 17.02.2017
 */
@Entity
@Table(name = "Person3")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // tabela od klasy
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) // musi byc tryb generowania TABLE, domysli da wyjatek
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
