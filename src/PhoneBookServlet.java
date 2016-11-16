import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class PhoneBookServlet extends HttpServlet {

    private ConcurrentHashMap<String, LinkedList<String>> book = new ConcurrentHashMap<>();//имя, номера

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        init();
       // getServletContext().getRealPath()
       // String source = PhoneBookServlet.class.getResource("phones.txt").toString(); //class.getResourceAsStream("phones.txt");
        Scanner input = new Scanner(new FileReader("phones.txt"));
        //считывание из файла
        while (input.hasNext()) {
            String name = input.next();
            LinkedList<String> phones = new LinkedList<>();

            while (!input.hasNextLine()) {
                phones.add(input.next());
            }
            //строка кончилась
            book.put(name, phones);
        }

 response.setContentType("text/html");
 response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>PhoneBook</title>");
        out.println("</head>");
        out.println("<body>");
        //записи из книги
  /*      for(Map.Entry<String, LinkedList<String>> entry : book.entrySet()){
            out.println(entry.getKey() + " " + getPhones(entry.getValue()));
        }
*/
        out.println("Witch --- *** --- ");
        ///
        out.println("</body>");
        out.println("</html>");
    }

    private String getPhones(LinkedList<String> list) {
        StringBuilder str = new StringBuilder();
        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            str.append(it.next());
            //у последнего без запятой
            if (it.hasNext())
                str.append(", ");
        }
        return str.toString();
    }
}
