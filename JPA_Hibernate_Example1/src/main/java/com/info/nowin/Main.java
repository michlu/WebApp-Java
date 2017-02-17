package com.info.nowin;


import com.info.nowin.manyToMany.Project;
import com.info.nowin.oneToManyUnidirectional.Phone;
import com.info.nowin.oneToOneBidirectional.Cat;
import com.info.nowin.oneToOneBidirectional.Owner;
import com.info.nowin.singleTableInheritance.Profesor;
import com.info.nowin.singleTableInheritance.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDataBase"); // nazwa z konfiguracji w persistence.xml
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        //1 Paczka "domain"
        // dodanie danych pracownika - podzielona na dwie tabele w DB
        com.info.nowin.domain.Employee employee1 = new com.info.nowin.domain.Employee();
        employee1.setFirsNtame("Paula");
        employee1.setLastName("Maruda");
        employee1.setSalary(3333.0);
        employee1.setLocality("Warszawa");
        employee1.setZipCode("11-111");
        employee1.setStreet("Koszykowa");
        employee1.setStreetNumber(666);


        //2 paczka "embeddedClass"
        // Dodanie danych z klasa osadzona "adress" - w DB wystepuje jako jedna tabela
        com.info.nowin.embeddedClass.Employee employee2 = new com.info.nowin.embeddedClass.Employee();
        com.info.nowin.embeddedClass.Adress adress2 = new com.info.nowin.embeddedClass.Adress();
        employee2.setFirsNtame("Tobiasz");
        employee2.setLastName("Beczkowski");
        employee2.setSalary(4567.0);
        employee2.setAdress(adress2);
        adress2.setLocality("Wroclaw");
        adress2.setZipCode("22-111");
        adress2.setStreet("Rynkowa");
        adress2.setStreetNumber(78);

        //3 paczka "oneToOne"
        // Klasa z relacjami jeden do jednego - dwie tabele employee i adress powiazane
        com.info.nowin.oneToOne.Employee employee3 = new com.info.nowin.oneToOne.Employee();
        com.info.nowin.oneToOne.Adress adress3 = new com.info.nowin.oneToOne.Adress();
        employee3.setFirsNtame("Maurycy");
        employee3.setLastName("Mankowski");
        employee3.setSalary(789.0);
        employee3.setAdress(adress3); // nalezy oba obiekty zapisac w managerze encji
        adress3.setLocality("Poznan");
        adress3.setZipCode("52-781");
        adress3.setStreet("Solidarnosci");
        adress3.setStreetNumber(89);

        //4 paczka "findAndModify"
        // dodawanie danych, wyszukanie rekordu i zmiana danych
        // nalezy skasowac <property name="hibernate.hbm2ddl.auto" value="create"/> z pliku xml
        com.info.nowin.findAndModify.Employee employee4 = new com.info.nowin.findAndModify.Employee();
        employee4.setFirsNtame("Romuald");
        employee4.setLastName("Kopernik");
        employee4.setSalary(1000.0);

        //5 paczka "refreshingObject
        // do DB dodajemy tiggera (automatyczny skrypt).. aby w programie Java bylo widac jego efekt trzeba odswiezyc klase poprzez manager encji
        com.info.nowin.refreshingObject.Employee employee5 = new com.info.nowin.refreshingObject.Employee();
        employee5.setFirsNtame("Antoni");
        employee5.setLastName("Klepka");
        employee5.setSalary(2000.0);


        //6 paczka "oneToOneBidirectional
        // dwie tabele powiazane ze soba one to one / dwukierunkowa (map
        Owner owner1 = new Owner();
        Cat cat1 = new Cat();
        owner1.setFirstName("Jan");
        owner1.setLastName("Nowak");
        cat1.setName("Roman");
        owner1.setCat(cat1);

        //7 paczka "oneToManyUnidirectional
        // dwie tabele powiazane jeden do wielu / jednokierunkowy
        com.info.nowin.oneToManyUnidirectional.Employee employee6 = new com.info.nowin.oneToManyUnidirectional.Employee();
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        employee6.setFirsNtame("Zbigniew");
        employee6.setLastName("Maj");
        employee6.setSalary(700.0);
        phone1.setType("mobile");
        phone2.setType("home");
        phone1.setNumber("666 666 666");
        phone2.setNumber("299 99 99");
        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);
        employee6.setPhones(phones); // przekazanie listy telefonow

        //8 paczka "oneToManyBidirectional"
        // relacja jeden do wielu, z powiazaniem wielu do konkretnej tabeli ownera
        // do employee nie przekazujemy listy, jednak powiazania sie tworza
        com.info.nowin.oneToManyBidirectional.Employee employee7 = new com.info.nowin.oneToManyBidirectional.Employee();
        employee7.setFirsNtame("Zbyszek");
        employee7.setLastName("Kieliszek");
        employee7.setSalary(200.0);
        com.info.nowin.oneToManyBidirectional.Phone phoneB1 = new com.info.nowin.oneToManyBidirectional.Phone();
        com.info.nowin.oneToManyBidirectional.Phone phoneB2 = new com.info.nowin.oneToManyBidirectional.Phone();
        phoneB1.setType("mobile");
        phoneB1.setNumber("123345456");
        phoneB1.setEmployee(employee7);
        phoneB2.setType("home");
        phoneB2.setNumber("123566433");
        phoneB2.setEmployee(employee7);

        //9 paczka "manyToMany"
        // relacja wielu do wielu
        // generator identyfikatorow GenerationType.TABLE
        Project project1 = new Project();
        Project project2 = new Project();
        project1.setName("Projekt 1");
        project2.setName("Projekt 2");
        com.info.nowin.manyToMany.Employee employee8 = new com.info.nowin.manyToMany.Employee("Angelika", "Ubysz", 150.0);
        com.info.nowin.manyToMany.Employee employee9 = new com.info.nowin.manyToMany.Employee("Mariola", "Kowalska", 350.0);
        com.info.nowin.manyToMany.Employee employee10 = new com.info.nowin.manyToMany.Employee("Emilia", "Umańska", 450.0);
        com.info.nowin.manyToMany.Employee employee11 = new com.info.nowin.manyToMany.Employee("Krystian", "Bednarek", 190.0);
        com.info.nowin.manyToMany.Employee employee12 = new com.info.nowin.manyToMany.Employee("Mateusz", "Szczesniak", 450.0);

        List<com.info.nowin.manyToMany.Employee> employees1 = new ArrayList<>();
        employees1.add(employee8);
        employees1.add(employee9);
        employees1.add(employee10);
        List<com.info.nowin.manyToMany.Employee> employees2 = new ArrayList<>();
        employees2.add(employee8); // wykorzystany do dwoch projektow
        employees2.add(employee11);
        employees2.add(employee12);

        project1.setEmployees(employees1); // dodanie pracownikow do projektow
        project2.setEmployees(employees2);


        //10 paczka singleTableInheritance
        // sposob dziedziczenia, tworzenie jednej tabeli (person)
        Profesor profesor1 = new Profesor();
        profesor1.setFirstName("Paweł");
        profesor1.setLastName("Nowak");
        profesor1.setSalary(9000.0);

        Student student1 = new Student();
        student1.setFirstName("Krzysztof");
        student1.setLastName("Kononowicz");
        student1.setAvarageGrade(4.75);

        //11 paczka joinedInheritance
        // dziedziczenie - kazda klasa ma osobne tabele (3 tabele person, student, profesor)

        com.info.nowin.joinedInheritance.Profesor profesor2 = new com.info.nowin.joinedInheritance.Profesor();
        profesor2.setFirstName("Marian");
        profesor2.setLastName("Ksiazek");
        profesor2.setSalary(5000.0);
        com.info.nowin.joinedInheritance.Student student2 = new com.info.nowin.joinedInheritance.Student();
        student2.setFirstName("Dawid");
        student2.setLastName("Gaugan");
        student2.setAvarageGrade(2.75);

        //12 paczka tablePerClassInheritance
        // dziedziczenie - z trzech klas tworze dwie klasy konkretne (2 tabele student i profesor obydwie klasy dziedzicza po klasie person)

        com.info.nowin.tablePerClassInheritance.Profesor profesor3 = new com.info.nowin.tablePerClassInheritance.Profesor();
        profesor3.setFirstName("Donald");
        profesor3.setLastName("Misiewicz");
        profesor3.setSalary(6000.0);
        com.info.nowin.tablePerClassInheritance.Student student3 = new com.info.nowin.tablePerClassInheritance.Student();
        student3.setFirstName("Hieronim");
        student3.setLastName("Jaszewski");
        student3.setAvarageGrade(3.75);


        // TRANZAKCJA:
        entityManager.getTransaction().begin(); // zaczynamy tranzakcje
        entityManager.persist(employee1);   //1
        entityManager.persist(employee2);   //2
        entityManager.persist(employee3);   //3
        entityManager.persist(adress3);     //3
        entityManager.persist(employee4);    //4
        entityManager.persist(employee5);    //5
        entityManager.persist(owner1);      //6
        entityManager.persist(cat1);        //6
        entityManager.persist(employee6);   //7
        entityManager.persist(phone1);      //7
        entityManager.persist(phone2);      //7
        entityManager.persist(employee7);      //8
        entityManager.persist(phoneB1);      //8
        entityManager.persist(phoneB2);      //8

        entityManager.persist(project1);       //9
        entityManager.persist(project2);    //9
        entityManager.persist(employee8);   //9
        entityManager.persist(employee9);   //9
        entityManager.persist(employee10);  //9
        entityManager.persist(employee11);  //9
        entityManager.persist(employee12);  //9

        entityManager.persist(profesor1); //10
        entityManager.persist(student1); //10

        entityManager.persist(profesor2); //11
        entityManager.persist(student2); //11

        entityManager.persist(student3); //12
        entityManager.persist(profesor3); //12

        entityManager.getTransaction().commit(); // komitujemy tranzakcje


        //5 odswiezenie danych zmienonych w DB
        entityManager.refresh(employee5);    //5


        // KASOWANIE OBIEKTU:
        entityManager.getTransaction().begin();
        entityManager.remove(employee5);    //5
        entityManager.getTransaction().commit();


        entityManager.refresh(cat1);


        // zamkniecie managerow encji
        entityManager.close();
        entityManagerFactory.close();
    }
}
