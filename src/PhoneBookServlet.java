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
* */

public class PhoneBookServlet extends HttpServlet {

    private Book book = new Book();

    //исполняется 1 раз при запуске
   /* public void init() throws ServletException {
        book = new Book();
    }*/

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String uri = request.getRequestURI();

        if(uri.equals("/servlet/PhoneBook/add")) {
            book.add(request.getParameter("name"), request.getParameter("phone"));
        }
       /* else if(uri.equals("/servlet/PhoneBook/save")){
            //сохранить в файл - не сохраняет
            out.println("<p>" + book.save() + "</p>");
        }*/

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>PhoneBook</title>");
        out.println("</head>");
        out.println("<body>");
       // out.println("uri: " + uri);
      //  out.println("<form method=\"GET\" action=\"/servlet/PhoneBook/save\"><input type=\"submit\" value=\"save\"></form>");
        out.println("<form method=\"get\" action=\"/servlet/PhoneBook/add\">");
        out.println("name: <input type=\"text\" name=\"name\">");
        out.println("phone number: <input type=\"text\" name=\"phone\">");
        out.println(" <input type=\"submit\" value=\"add to list\">");
        out.println("</form>");

        out.println(book.getBook());
        out.println("</body>");
        out.println("</html>");
    }


}
