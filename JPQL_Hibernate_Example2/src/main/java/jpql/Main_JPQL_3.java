package jpql;

/**
 * Funkcje w JQPL: agv, sum, min/max, count, substring, trim, lower/upper, length [JPA i Hibernate 20]
 */
import jpql.domain.Employee;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;


public class Main_JPQL_3 {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("myDataBase");
        entityManager = entityManagerFactory.createEntityManager();
        addEmployees();

        // operacje na liczbach:
        Query query = entityManager
                .createQuery("SELECT avg(e.salary), min(e.salary), max(e.salary), sum(e.salary), count(e) FROM Employee e");
        Object[] result = (Object[]) query.getSingleResult();
        System.out.println("Srednia: " + result[0]);
        System.out.println("Najnizsza: " + result[1]);
        System.out.println("Najwyzsza: " + result[2]);
        System.out.println("Suma: " + result[3]);
        System.out.println("Ilosc Pracownikow: " + result[4]);


        // operacje na stringach:
        Query query1 = entityManager
                .createQuery("SELECT substring(e.firstName, 1, 3), trim(e.lastName), lower(e.firstName), upper(e.firstName), length(e.firstName) FROM Employee e WHERE e.firstName = 'Karol'");
        Object[] result1 = (Object[]) query1.getSingleResult();
        System.out.println("Pierwsze trzy litery imienia: " + result1[0]);
        System.out.println("Nazwisko bez spacji: |" + result1[1] + "|");
        System.out.println("Imie malymi literami " + result1[2]);
        System.out.println("Imie duzymi literami " + result1[3]);
        System.out.println("Dlugosc imienia " + result1[4]);


        entityManager.close();
        entityManagerFactory.close();
    }

    private static void addEmployees() {
        addEmployee("Karol", " Mateusiak", 2633);
        addEmployee("Marika", "Bednarek", 2345);
        addEmployee("Jan", "Mateusiak", 7346);
        addEmployee("Daria", "Kowalska", 2352);
        addEmployee("Monika", "Ucińska", 4263);
        addEmployee("Ernest", "Pająk", 2634);
        addEmployee("Kamil", "Stępień", 2345);
        addEmployee("Przemek", "Maciejewski", 5433);
        addEmployee("Robert", "Woźniak", 3324);
        addEmployee("Agnieszka", "Nowak", 2000);
        addEmployee("Angelika", "Bednarska", 1000);
    }

    private static void addEmployee(String firstName, String lastName, double salary) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setSalary(salary);

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }
}
