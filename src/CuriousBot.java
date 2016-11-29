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

        new Thread(() -> {
            CuriousBot bot1 = new CuriousBot();
            while (true){
                try {
                    bot1.readBook();
                } catch (IOException e) {
                    System.out.println("can't connect to PhoneBook");
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            CuriousBot bot2 = new CuriousBot();
            while(true){
                try {
                    bot2.addToBook();
                } catch (IOException e) {
                    System.out.println("can't add to PhoneBook");
                }
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
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
        System.out.println("adding " + name + " with (333)818-55" + rand + num);//просто проверка

        URL url = new URL("http://localhost:8880/servlet/PhoneBook/add?name=" + name + "&phone=(333)818-55" + rand + num);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        http.setRequestMethod("GET");
        http.setDoInput(true);
        http.getResponseCode();
        //System.out.println("Response Code : " + http.getResponseCode() + " " + http.getResponseMessage());
    }

}
