package com.nowin.spring.dataacces;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class ProductsRepository {
    private JdbcTemplate jdbcTemplate;

    // Templajt do parametrow nazywanych
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

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


    // Przekazywanie parametrow nazwanych
    public List<Map<String, Object>> getProductsWithPriceRage(double minPrice, double maxPrice){
        String sql = "SELECT * FROM products WHERE buyPrice >= :minPrice AND buyPrice <= :maxPrice";
        SqlParameterSource parameters = new MapSqlParameterSource("minPrice", minPrice).addValue("maxPrice", maxPrice); // dwie mozliwosci podawania atrybutow

//        Druga metoda przekazywania parametrow:
//        Map<String, Object> params = new HashMap<>();
//        params.put("minPrice", minPrice);
//        params.put("maxPrice", maxPrice);
//        parameters = new MapSqlParameterSource(params);

//          dla przekazania jednego argumentu, mozna uzyc:
//        Map<String, Double> minPrice1 = Collections.singletonMap("minPrice", minPrice);

//        ewentualnie mozna od razdu dodac mape hash:
//        namedParameterJdbcTemplate.queryForList(sql, params);
        return namedParameterJdbcTemplate.queryForList(sql, parameters);
    }

    public List<Map<String, Object>> getProductsWithProductLine(List<String> productLines){
        String sql = "SELECT * FROM products WHERE productLine IN (:productLines)";
        Map<String, List<String>> parameters = Collections.singletonMap("productLines", productLines);

        return namedParameterJdbcTemplate.queryForList(sql, parameters);
    }
}
