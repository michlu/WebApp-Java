
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.nowin.model.Book;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String option = request.getParameter("option");
        BookDAO dao = new BookDAO();
        Book book = null;
        String operation = null;
        boolean result = false;
        // opcja z checkbox'a
        if("search".equals(option)) {
            book = dao.read(isbn);
            result = book!=null? true:false;
            operation = "search";
        } else if("add".equals(option)) {
            book = new Book(isbn, title, description);
            result = dao.create(book);
            operation = "add";
        } else if("update".equals(option)) {
            book = new Book(isbn, title, description);
            result = dao.update(book);
            operation = "update";
        } else if("delete".equals(option)) {
            book = new Book(isbn, title, description);
            result = dao.delete(book);
            operation = "delete";
        }
        if(book != null && result) {
            request.setAttribute("option", operation); // String okreslajacy operacje na stronie rezultatu
            request.setAttribute("book", book); // Przekazanie obiektu ksiazki do strony rezultatu
            // Potwierdzenie
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } else {
            // Strona bledu
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}