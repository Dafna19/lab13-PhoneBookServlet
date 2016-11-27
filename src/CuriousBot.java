import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Используя класс HttpUrlConnection
 * необходимо написать имитатора пользователя,
 * который раз в 5 секунд запрашивает записную книжку,
 * а раз в 15 секунд добавляет туда нового пользователя.
 */
public class CuriousBot {
    public static void main(String[] args) {
        CuriousBot bot = new CuriousBot();

        try {
            bot.readBook();
        } catch (IOException e) {
            System.out.println("can't connect to PhoneBook");
            e.printStackTrace();
        }

        try {
            bot.addToBook();
        } catch (IOException e) {
            System.out.println("can't add to PhoneBook");
            e.printStackTrace();
        }
    }

    //запрашивает записную книжку
    private void readBook() throws IOException {
        URL url = new URL("http://localhost:8880/servlet/PhoneBook");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        http.getResponseCode();
        //System.out.println("Response Code : " + http.getResponseCode() + " " + http.getResponseMessage());
    }

    private void addToBook() throws IOException {
        int rand = (int) (Math.random() * 10);
        int num = (int) (Math.random() * 10);

        String name;
        if (rand > 5)
            name = "name" + rand;
        else
            name = "user" + rand;
        //System.out.println(name);

        URL url = new URL("http://localhost:8880/servlet/PhoneBook/add?name=" + name + "&phone=(333)818-55" + rand + num);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        http.setRequestMethod("GET");
        http.setDoInput(true);
        http.getResponseCode();
        //System.out.println("Response Code : " + http.getResponseCode() + " " + http.getResponseMessage());
    }

}
