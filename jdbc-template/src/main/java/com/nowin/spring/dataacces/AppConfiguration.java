package com.nowin.spring.dataacces;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.nowin.spring.dataacces")
public class AppConfiguration {

    @Bean
    public DataSource dataSource(){
        MysqlDataSource dataSource  = new MysqlDataSource();
        dataSource.setDatabaseName("classicmodels");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setUser("root");
        dataSource.setPassword("admin");
        //   druga mozliwosc:
        //   dataSource.setURL("jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
