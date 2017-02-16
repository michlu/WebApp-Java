package com.info.nowin.manyToMany;

import javax.persistence.*;
import java.util.List;

/**
 * @author michlu
 * @sience 16.02.2017
 */

@Entity
public class Project {

    // Generowanie identyfkatorow
    @Id
    @TableGenerator(name = "mojGenerator",
                    table = "tabela_z_identyfikatorami",
                    pkColumnName = "nazwa_sekwencji",
                    valueColumnName = "wartosc_identyfikatora",
                    pkColumnValue = "id_projektu",
                    initialValue = 10, // od jakiej lczby zaczac liczenie
                    allocationSize = 15)    // przeskok numeryczny id po dodaniu kolejnych danych
    @GeneratedValue (strategy = GenerationType.TABLE, generator = "mojGenerator") // numeracja id w obrebie tylko tej tabeli / podanie szablonu generatora w atrybusie generator
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "pracownicy_w_projektach", // nazwa tabeli laczacej pracownikow z projektami
            joinColumns = {@JoinColumn(name = "id_projektuMtM")}, // idenntyfikatory dla projektow
            inverseJoinColumns = {@JoinColumn(name = "id_pracownikaMtM")}
    )
    List<Employee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
