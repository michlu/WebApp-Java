package info.nowin.dataaccess.jdbc;

import java.sql.*;
import java.util.Properties;

public class JdbcMain_method1 {

    public static final String USER = "root";
    public static final String PASSWORD = "admin";
    /*
    JEZELI WYSKAKUJE ERROR:
    "The server time zone value 'środkowoeuropejski czas stand' is unrecognized or represents more than one time zone.
    You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc
    time zone value if you want to utilize time zone support."

    NALEZY DODAC W URL PO NAZWIE BAZDY DANYCH LINIJKE:
    ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
     */
    public static final String URL = "jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("useSSL", "false");
        properties.setProperty("autoReconnect", "true");
        try {
            Connection connection = DriverManager.getConnection(URL, properties);
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
