package com.info.nowin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author michlu
 * @sience 18.02.2017
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(DataBaseConfiguration.class);
    }
}
