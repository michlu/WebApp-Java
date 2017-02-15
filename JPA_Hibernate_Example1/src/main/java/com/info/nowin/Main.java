package com.info.nowin;


import com.info.nowin.oneToManyUnidirectional.Employee;
import com.info.nowin.oneToManyUnidirectional.Phone;
import com.info.nowin.oneToOneBidirectional.Cat;
import com.info.nowin.oneToOneBidirectional.Owner;

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
