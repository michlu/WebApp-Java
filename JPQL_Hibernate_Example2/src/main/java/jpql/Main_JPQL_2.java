package jpql;

/**
 * przekazywanie parametrow w JPQL przez indeks i nazwe, przekazywanie listy jako parametr
 */
import jpql.domain.Employee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main_JPQL_2 {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("myDataBase");
        entityManager = entityManagerFactory.createEntityManager();

        addEmployees();

        // PARAMETRY PODAWANE PRZEZ NAZWANE (:minSalary)
        // pracownicy ktorzy maja pensje wieksza niz ...
        TypedQuery<Employee> query1 = entityManager.createQuery("SELECT e FROM Employee e WHERE e.salary > :minSalary", Employee.class);
        query1.setParameter("minSalary", 5000.0);

        for (Employee employee : query1.getResultList()) {
            System.out.println("query 1 : " + employee.getFirstName());
            System.out.println("query 1 : " + employee.getLastName());
            System.out.println("query 1 : " + employee.getSalary() + "\n");
        }


        // PARAMETRY PODAWANE PRZEZ INDEXY Z NUMERACJA (?1, ?2)
        // pracownicy z pensjami miedzy 2000 a 3000
        TypedQuery<Employee> query2 =
                entityManager.createQuery("SELECT e FROM Employee e WHERE e.salary > ?1 AND e.salary < ?2", Employee.class);

        query2.setParameter(1, 2000.0);
        query2.setParameter(2, 3000.0);

        for (Employee employee : query2.getResultList()) {
            System.out.println("query 2 : " + employee.getFirstName());
            System.out.println("query 2 : " + employee.getLastName());
            System.out.println("query 2 : " + employee.getSalary() + "\n");
        }

        // PARAMETR PODANY PRZEZ LISTE
        // szukamy pracownikow o danych nazwiskach
        TypedQuery<Employee> query3 =
                entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName IN :names", Employee.class);
        // Lista nazwisk ktore nas interesuja
        List<String> names = new ArrayList<>();
        names.add("Mateusiak");
        names.add("Bednarek");
        // przekazujemy liste do indeksu
        query3.setParameter("names", names);

        for (Employee employee : query3.getResultList()) {
            System.out.println("query 3 : " + employee.getFirstName());
            System.out.println("query 3 : " + employee.getLastName());
            System.out.println("query 3 : " + employee.getSalary() + "\n");
        }

        entityManager.close();
        entityManagerFactory.close();
    }

    private static void addEmployees() {
        addEmployee("Karol", "Mateusiak", 2633);
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
