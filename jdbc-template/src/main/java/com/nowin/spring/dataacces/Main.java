package com.nowin.spring.dataacces;


import com.mysql.cj.api.x.Collection;
import com.nowin.spring.dataacces.office.Office;
import com.nowin.spring.dataacces.office.OfficesRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        /*
        Do wyboru pliki konfiguracyjne na dwa sposoby. Poprzez plik XML app-config, albo klase AppConfiguration
         */

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
//        ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
        ProductsRepository productsRepository = context.getBean("productsRepository", ProductsRepository.class);


        // TESTY ZAPYTAN:
        int numberOfProducts = productsRepository.getNumberOfProducts();
        System.out.println("Liczba produktow: " + numberOfProducts);

        int numberOfProductsWithPriceGreatherThan = productsRepository.getNumberOfProductsWithPriceGreatherThan(70.0);
        System.out.println("Liczba produktow: " + numberOfProductsWithPriceGreatherThan);

        Map<String, Object> product = productsRepository.getProductCode("S10_1678");
        System.out.println("Produkt code: " + product);

        List<Map<String, Object>> productsWithPriceGreaterThan = productsRepository.getProductsWithPriceGreaterThan(80.0);
        for (Map<String, Object> productMap : productsWithPriceGreaterThan) {
            System.out.println(productMap);
        }

        // tworze strumien z double/ zamieniam na double/ wyliczam srednia/ pobieram doubla
        double average = productsRepository.getProductPrices().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .getAsDouble();
        System.out.println(average);


        // Mapowanie do obiektu z zapytania
        OfficesRepository officesRepository = context.getBean("officesRepository", OfficesRepository.class);
        Office office = officesRepository.getofficeByOfficeCode("1");
        System.out.println("Zmapowany obiekt: " + office);

        // mapowanie do listy obiektow
        List<Office> allOffice = officesRepository.getAllOffice();
        for (Office office1 : allOffice) {
            System.out.println("Lista obiektów: " + office1);
        }

        // przyklad z parametrami "nazwanymi" i ich przekazywaniem
        List<Map<String, Object>> productsWithPriceRage = productsRepository.getProductsWithPriceRage(50.0, 60.);
        for (Map<String, Object> product1 : productsWithPriceRage) {
            System.out.println(product1);
        }

        // podkladanie listy jako parametru
        List<String> pro = Collections.singletonList("Motorcycles");
        List<Map<String, Object>> productsWithProductLine = productsRepository.getProductsWithProductLine(pro);
        for (Map<String, Object> product1 : productsWithProductLine) {
            System.out.println(product1);
        }
    }
}
