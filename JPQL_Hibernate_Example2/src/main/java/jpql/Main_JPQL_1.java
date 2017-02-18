package jpql;

/**
 * Podstawy zapytan JPQL - wydobywanie obiektow
 */
import jpql.domain.Employee;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;


public class Main_JPQL_1 {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("myDataBase");
        entityManager = entityManagerFactory.createEntityManager();

        addEmployees();

        // Budowanie zapytania:
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName = 'Pająk'", Employee.class);// druga wartosc oznacza co zwracamy
        // wyciaganie informacji dla jednego obiektu:
        Employee employee = query.getSingleResult();
        System.out.println("query 1 : " + employee.getFirstName());
        System.out.println("query 1 : " + employee.getLastName());
        System.out.println("query 1 : " + employee.getSalary() + "\n");


        // kilkla rezultatow zapytania przekazane do listy
        TypedQuery<Employee> query2 = entityManager.createQuery("SELECT e FROM Employee e WHERE e.salary > 3000 order by e.salary", Employee.class);

        List<Employee> employees = query2.getResultList();
        for (Employee employee1 : employees) {
            System.out.println("query 2 : " + employee1.getFirstName());
            System.out.println("query 2 : " + employee1.getLastName());
            System.out.println("query 2 : " + employee1.getSalary() + "\n");
        }

        // Zwraca tablice Object (nie wiadomo jaki typ zwroci)
        Query query3 = entityManager.createQuery("SELECT concat(e.firstName, ' ', e.lastName), e.salary * 0.2 FROM Employee e");
        Iterator<?> iterator = query3.getResultList().iterator();
        // do poki iteratora ma jakies obiekty, to je wyciagamy
        while (iterator.hasNext()){
            Object[] item = (Object[]) iterator.next();
            String name = (String) item[0];
            double tax = (double) item[1];
            System.out.println(name + " has to pay " + tax);
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
