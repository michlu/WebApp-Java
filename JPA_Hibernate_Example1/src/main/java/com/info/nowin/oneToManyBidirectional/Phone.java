package com.info.nowin.oneToManyBidirectional;

import javax.persistence.*;

/**
 * @author Michlu
 * @sience 2017-02-12
 */
@Entity
@Table(name = "Phone2")
public class Phone {

    @Id
    @GeneratedValue
    private long id;
    private String type;
    private String number;

    @ManyToOne
    @JoinColumn(name = "owner_id2") // musi byc tu gdzie jest adnotacja ManyToOne
    private Employee employeePhone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployee() {
        return employeePhone;
    }

    public void setEmployee(Employee employee) {
        this.employeePhone = employee;
    }
}
