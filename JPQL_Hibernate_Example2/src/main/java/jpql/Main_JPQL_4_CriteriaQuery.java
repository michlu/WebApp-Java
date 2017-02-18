package jpql;

/**
 * tworzenie zapytan bez uzycia jezyka SQL
 */

import jpql.domain.Employee;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;


public class Main_JPQL_4_CriteriaQuery {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("myDataBase");
        entityManager = entityManagerFactory.createEntityManager();
        addEmployees();

        // tworzymy takie zapytanie:
        // select e from Employye e where e.salary > 3000 and e.salary <5000

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class); // zapytanie z definiowanym obiektem zwrotnym (musimy wiedziec co zapytanie zwroci)

        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class); // z zapytania SQL: from Employee

        Path<Double> salary = employeeRoot.get("salary");

        // select - co bede wybieral, where - lista wyrazen do definiowania wyszukiwan
//        criteriaQuery.select(employeeRoot).where(criteriaBuilder.greaterThan(salary, 3000.0)); // da pracownikow z pensjami wiekszymi niz 3000
        criteriaQuery.select(employeeRoot).where(criteriaBuilder.and(criteriaBuilder.greaterThan(salary, 3000.0), criteriaBuilder.lessThan(salary, 5000.0))); // pracownicy z pensjami pomiedzy 3000 a 5000

        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        List<Employee> employees = query.getResultList();
        for (Employee emp : employees) {
            System.out.println(emp.getFirstName());
            System.out.println(emp.getLastName());
            System.out.println(emp.getSalary() + "\n");
        }

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
