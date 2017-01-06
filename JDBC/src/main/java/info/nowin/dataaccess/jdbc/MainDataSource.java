package info.nowin.dataaccess.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDataSource {

    public static final String DB_NAME = "world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String SERVER_NAME = "localhost";
    public static final String USER = "root";
    public static final String PASSWORD = "admin";

    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName(DB_NAME);
        dataSource.setServerName(SERVER_NAME); // host serwera
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Name, Population FROM city");

            String cityName = null;
            int cityPopulation = 0;
            while(resultSet.next()) {
                cityName = resultSet.getString("Name");
                cityPopulation = resultSet.getInt("Population");
                System.out.println(cityName + " " + cityPopulation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
