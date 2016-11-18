import com.sun.xml.internal.ws.server.ServerRtException;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * File file = new File(getServletContext().getRealPath("/abc.txt"));
 */
public class Book {
    private ConcurrentHashMap<String, LinkedList<String>> book = new ConcurrentHashMap<>();//имя, номера

    public Book() {
        try {
            InputStream inputFile = new FileInputStream("C:\\Users\\Наташа\\Dropbox\\Учёба\\прога\\ТехПрог\\13\\out\\artifacts\\13_war_exploded\\WEB-INF\\classes\\phones.txt");
            Scanner input = new Scanner(inputFile);
            //считывание из файла
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                String name = line.next();
                LinkedList<String> phones = new LinkedList<>();

                while (line.hasNext()) {
                    phones.add(line.next());
                }
                //строка кончилась
                book.put(name, phones);
            }
            System.out.println("can read file");
        }catch (FileNotFoundException e){
            System.out.println("file not found");
        }catch (NoSuchElementException n){
            System.out.println("no file");
        }
    }

    public void add(String name, String phone){
        if(book.containsKey(name)){//добавляем к существующему
            book.get(name).add(phone);
        }
        else {
            LinkedList<String> list = new LinkedList<>();
            list.add(phone);
            book.put(name, list);
        }
    }

    public String save() {
        try{
        FileWriter outputfile = new FileWriter("saved.txt");
        outputfile.write(getBook());
        outputfile.flush();
        }catch (IOException e){
            return "can't write to file";
        }
        return "saved";
    }

    //записи из книги
    public String getBook(){
        StringBuilder str = new StringBuilder();
        for(Map.Entry<String, LinkedList<String>> entry : book.entrySet()){
            str.append("<p>" + entry.getKey() + " " + getPhones(entry.getValue()) + "</p>");
        }
        return str.toString();
    }

    public String getPhones(LinkedList<String> list) {
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
