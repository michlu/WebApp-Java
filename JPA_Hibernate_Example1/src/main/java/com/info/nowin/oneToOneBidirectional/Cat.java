package com.info.nowin.oneToOneBidirectional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Michlu
 * @sience 2017-02-12
 */
@Entity
public class Cat {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToOne(mappedBy = "cat")  // powiazuje tego kota z atrybutem ownera
    private Owner owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
