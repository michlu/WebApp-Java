package com.nowin.spring.dataacces;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

        List<Map<String, Object>> productsWithPriceGreaterThan = productsRepository.getProductsWithPriceGreaterThan(70.0);
        for (Map<String, Object> productMap : productsWithPriceGreaterThan) {
            System.out.println(productMap);
        }
    }
}
