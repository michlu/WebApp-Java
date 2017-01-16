
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *  Jedynym zadaniem klasy "dostawcy" jest udostępnienie publicznej metody getConnection(). Implementujemy wzorzec singleton,
 *  który zadba o to, że tworzymy tylko jeden obiekt DataSource, a następnie z niego będa pobierane połaczenia
 */

public class ConnectionProvider {
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        return getDSInstance().getConnection();
    }

    private static DataSource getDSInstance() {
        if(dataSource == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/library");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }
}