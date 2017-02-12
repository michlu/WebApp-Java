package com.info.nowin.findAndModify;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Michlu
 * nalezy skasowac <property name="hibernate.hbm2ddl.auto" value="create"/> z pliku xml
 * Klasa ta ma wyszukac obiekt z bazy i go zmodyfikowac (JPA) dba o wyslanie zmian do DB
 */
public class FindAndModify {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDataBase"); // nazwa z konfiguracji w persistence.xml
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // Wyszukanie odpowiedniego rekordu z DB
        Employee employeeNr1 = entityManager.find(Employee.class, 5L); // wazne jaki podajemy klucz

        // Zmiana danych rekordu:
        employeeNr1.setSalary(3000.0);


        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
