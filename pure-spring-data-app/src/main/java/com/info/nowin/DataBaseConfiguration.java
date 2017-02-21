package com.info.nowin;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author michlu
 * @sience 18.02.2017
 */
@EnableJpaRepositories("com.info.nowin")
@ComponentScan("com.info.nowin") // skanowanie
@Configuration
public class DataBaseConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        // z jakiego zrodla bedzie korzystal (kto dostarcza implementacje JPA, gdzie szukac pakietow z encjami))
        containerEntityManagerFactoryBean.setDataSource(dataSource());
        containerEntityManagerFactoryBean.setPackagesToScan("com.info.nowin");
        containerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // ustawienie hibernate
        containerEntityManagerFactoryBean.setJpaProperties(getJpaProperties());
        return containerEntityManagerFactoryBean;
    }

    @Bean
    public Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("mojabaza3");
        dataSource.setUser("root");
        dataSource.setPassword("admin");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
