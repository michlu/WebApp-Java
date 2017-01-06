/**
 * @author Michlu
 * @sience 2017-01-06
 *
 * SQL injection – metoda ataku komputerowego wykorzystująca lukę w zabezpieczeniach aplikacji polegającą na
 * nieodpowiednim filtrowaniu lub niedostatecznym typowaniu danych użytkownika, które to dane są później wykorzystywane
 * przy wykonaniu zapytań (SQL) do bazy danych.
 *
 *  SPOSOB OCHRONY POPRZEZ - PreparedStatement
 *
 */
import java.sql.PreparedStatement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Connection conn = null;
        ResultSet resultSet = null;
//        Statement statement = null;

        PreparedStatement prepStmt = null;

        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/users");
            conn = ds.getConnection();

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            /*  NIE UZYWAC W TEJ WERSJI, POPRAWNA PONIZEJ
            statement = conn.createStatement();
            // pass2" OR '1'='1'; --
            final String sqlQuery = "SELECT username, password FROM user WHERE "
                    +"username=" + "\"" + username + "\" "
                    +"AND "
                    +"password=" + "\"" + password + "\";";
            System.out.println(sqlQuery);
            resultSet = statement.executeQuery(sqlQuery);
            */

            // POPRAWNIE uzywac PreparedStatment
            prepStmt = conn.prepareStatement("SELECT username, password FROM user WHERE username=? AND password=?;");
            prepStmt.setString(1, username);
            prepStmt.setString(2, password);
            System.out.println(prepStmt);
            resultSet = prepStmt.executeQuery();


            if(resultSet.next()) {
                String userFound = resultSet.getString("username");
                request.getSession().setAttribute("username", userFound);
                if("admin".equals(userFound)) {
                    request.getSession().setAttribute("privigiles", "all");
                } else{
                    request.getSession().setAttribute("privigiles", "view");
                }
            } else {
                request.getSession().setAttribute("username", "Nieznajomy");
                request.getSession().setAttribute("privigiles", "none");
            }
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                //statement.close();
                prepStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}