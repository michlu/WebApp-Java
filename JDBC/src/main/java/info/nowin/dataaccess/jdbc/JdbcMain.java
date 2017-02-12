package info.nowin.dataaccess.jdbc;

/**
 * @author Michlu
 * @sience 2017-01-06
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcMain {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        //Sterowniki bazy danych:
        final String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver); // zaladowanie klasy Driver

        // polaczenie z baza danych pobierajac obiekt Connection z klasy DriverManager:
        final String dbPath = "jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection conn = DriverManager.getConnection(dbPath, "root", "admin");

        // Zapytania do bazy danych można wykonywać poprzez obiekt klasy Statement, który pobierzemy z obiektu Connection:
        Statement statement = conn.createStatement();
        final String sqlQuery = "SELECT Name, Population FROM city";

        /*
        Na obiekcie Statement będziemy wykonywali głównie jedną z dwóch metod:

        executeQuery(String sqlQuery) - metoda, która zwraca w wyniku zbiór wyników otrzymanych w odpowiedzi z bazy w
                                        postaci obiektu ResultSet. Będzie więc użyteczna w przypadku zapytań typu SELECT.
        executeUpdate(String sqlQuery) - metoda przeznaczona do wykonywania zapytań aktualizacji danych - INSERT, UPDATE,
                                         DELETE. W wyniku zwraca ilość rekordów, które dotknęła zmiana (zapewne pamiętasz
                                         podobny wydruk w konsoli MySQL Workbencha).
         */
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        /*
         ResultSet to obiekt reprezentujący zbiór danych zwrócony przez MySQL. Można po nim iterować odwołując się do metody
         next(), która zwraca true lub false w zależności od tego, czy istnieje jeszcze jakiś element w kolekcji. Dane
         można wygodnie wyciągać odwołując się do metod getString(String colName), getInt(String colName) lub analogicznie
         innych typów zgodnych z typami kolumn w bazie danych, podając jako ich argument nazwę kolumny, której dane dla
         danego wiersza chcemy pobrać. W naszym przypadku pobieraliśmy informacje z kolumn Name i Population, więc też z
         nich pobieramy dane. Klasa ResultSet zajmuje się odpowiednią konwersją typów, my musimy jedynie zadbać o to, żeby
         nie odwoływać się przykładowo poprzez metodę getInt() do kolumny typu VARCHAR.
         */

        String cityName = null;
        int cityPopulation = 0;
        while(resultSet.next()) {
            cityName = resultSet.getString("Name");
            cityPopulation = resultSet.getInt("Population");
            System.out.println(cityName + " " + cityPopulation);
        }

        //po zakończeniu pracy z bazą danych trzeba pamietac o zamknięciu połączenia i obiektów Statement i ResultSet powiązanych z danym połączeniem
        if(statement != null) {
            statement.close();
        }
        if(resultSet != null) {
            resultSet.close();
        }
        if(conn != null) {
            conn.close();
        }

    }
}