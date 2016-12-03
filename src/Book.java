import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * File file = new File(getServletContext().getRealPath("/abc.txt"));
 * <p>
 * для теха:
 * miktex
 * связка miktex + texnic center
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
            inputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (NoSuchElementException n) {
            System.out.println("no file");
        } catch (IOException e) {
            System.out.println("error while closing the file");
        }
    }

    public void add(String name, String phone) {
        if (book.containsKey(name)) {//добавляем к существующему
            if (!book.get(name).contains(phone))
                book.get(name).add(phone);
        } else {
            LinkedList<String> list = new LinkedList<>();
            list.add(phone);
            book.put(name, list);
        }
    }

    public String save() {
        try {
            FileWriter outputfile = new FileWriter("C:\\Users\\Наташа\\Dropbox\\Учёба\\прога\\ТехПрог\\13\\out\\artifacts\\13_war_exploded\\WEB-INF\\classes\\phones.txt");
            for (Map.Entry<String, LinkedList<String>> entry : book.entrySet()) {
                outputfile.write( entry.getKey() + " " + getPhones(entry.getValue(), false) + "\n");
                outputfile.flush();
            }
            outputfile.close();
        } catch (IOException e) {
            return "can't write to file";
        }
        return "saved";
    }

    public void remove(String name){
        book.remove(name);
    }

    //записи из книги
    public String getBook() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, LinkedList<String>> entry : book.entrySet()) {
            str.append("<p>" + entry.getKey() + " " + getPhones(entry.getValue(), true) + "</p>");
        }
        return str.toString();
    }

    private String getPhones(LinkedList<String> list, boolean comma) {
        StringBuilder str = new StringBuilder();
        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            str.append(it.next());
            //у последнего без запятой
            if (it.hasNext() && comma)
                str.append(", ");
            else if (it.hasNext() && !comma)
                str.append(" ");
        }
        return str.toString();
    }

}
