package com.nowin.spring.dataacces.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * @author Michlu
 * @sience 2017-02-11
 */
@Repository
public class OfficesRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Office getofficeByOfficeCode(String officeCode) {
        String sql = "SELECT * FROM offices WHERE officeCode = ?";
        return jdbcTemplate.queryForObject(sql, new OfficeRowMapper(), officeCode);
    }

    public List<Office> getAllOffice(){ // mapowanie do listy
        String sql = "SELECT * FROM offices";
        return jdbcTemplate.query(sql, new OfficeRowMapper()); // SAMO query, nie queryForListy !!!
    }

    private static class OfficeRowMapper implements RowMapper<Office> {  // dziala dla pojedynczego wyiersza
        @Override
        public Office mapRow(ResultSet resultSet, int i) throws SQLException { // mapuje kolejne wiersze (wyciaga sie informacje z wiersza za pomoca resultSet, i to numer wiersza
            // zwraca pozadany obiekt, tworzymy go za pomoca wycagania informacji:
            Office office = new Office();
            office.setOfficeCode(resultSet.getString("officeCode"));
            office.setAddressLine1(resultSet.getString("addressLine1"));
            office.setAdressLine2(resultSet.getString("addressLine2"));
            office.setCity(resultSet.getString("city"));
            office.setCountry(resultSet.getString("country"));
            office.setPhone(resultSet.getString("phone"));
            office.setState(resultSet.getString("state"));
            office.setPostalCode(resultSet.getString("postalCode"));
            office.setTerritory(resultSet.getString("territory"));
            return office;
        }
    }
}
