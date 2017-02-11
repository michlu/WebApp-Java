package com.nowin.spring.dataacces;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int getNumberOfProducts() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM products", Integer.class);
    }

    public int getNumberOfProductsWithPriceGreatherThan(double priceLimit){
        String sql = "SELECT COUNT(*) FROM products WHERE buyPrice > ?";            // przekazanie parametru pod ?
        return jdbcTemplate.queryForObject(sql, Integer.class, priceLimit);         // queryForObject daje proste obiekty typu int, String itp.
    }

    /* NIE DZIALA
    public int getNumberOfProductsWithLine(List<String> productLines){
        String sql = "SELECT COUNT(*) FROM products WHERE productLine IN (?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, productLines);
    }
    */

    // Pobieranie Mapy DB
    public Map<String, Object> getProductCode(String productCode){
        String sql = "SELECT productCode, productLine, productName, buyPrice FROM products WHERE productCode = ?";
        return jdbcTemplate.queryForMap(sql, productCode);      // queryForMap daje mape
    }

    // Pobieranie Listy map z DB
    public List<Map<String, Object>> getProductsWithPriceGreaterThan(double priceLimit){
        String sql = "SELECT * FROM products WHERE buyPrice > ?";
        return jdbcTemplate.queryForList(sql, priceLimit);
    }

    public List<Double> getProductPrices() {
        String sql = "SELECT buyPrice FROM products";
        return jdbcTemplate.queryForList(sql, Double.class);
    }
}
