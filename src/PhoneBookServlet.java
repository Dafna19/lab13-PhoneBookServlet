import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/*
* Используя класс HttpUrlConnection
* необходимо написать имитатора пользователя,
* который раз в 5 секунд запрашивает записную книжку,
* а раз в 15 секунд добавляет туда нового пользователя.
*
* Вверху страницы ссылки --- добавить. Каждая из ссылок
* ведет на отдельную страницу,
* где с помощью элементов <input type="text" name="username" />
* в форме вводятся необходимые данные.
* Для отправки данных сервлету есть кнопка submit. *
*
* чтобы нормально открывался AddName.html,
* он должен лежать в папке web
*
* */

public class PhoneBookServlet extends HttpServlet {

    private Book book = new Book();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String uri = request.getRequestURI();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        out.println("<html><head>");
        out.println("<title>PhoneBook</title>");
        out.println("</head><body>");
        out.println("<a href=\"http://localhost:8880/AddName.html\">Add new</a>");

        if (uri.equals("/servlet/PhoneBook/add")) {
            String name = request.getParameter("name");
                book.add(name, request.getParameter("phone"));
        }

       /* else if(uri.equals("/servlet/PhoneBook/save")){
            //сохранить в файл - не сохраняет
            out.println("<p>" + book.save() + "</p>");
        }*/

        // out.println("uri: " + uri);
        //  out.println("<form method=\"GET\" action=\"/servlet/PhoneBook/save\"><input type=\"submit\" value=\"save\"></form>");

        out.println(book.getBook());
        out.println("</body></html>");
    }


}
