package javaCoreModule13.httpPractice;

import java.io.*;
import java.net.HttpURLConnection; //клас HttpURLConnection з пакету java.net може використовуватися для того,
// щоб надсилати з Java програми HTTP запити.
import java.net.URL;
import java.nio.file.Files;


public class Test {
    private static final String TEST_URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException {
        sendGET();
        sendPOST();
    }

    private static void sendGET() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
    }

    private static void sendPOST() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(Files.readAllBytes(new File("src/main/java/javaCoreModule13/user.json").toPath()));
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        System.out.println("POST response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
}

/**
 * Основні моменти у роботі з HttpUrlConnection:
 *
 * Адреса сайту, до якого ви хочете звернутися, вказується у конструкторі класу java.net.URL.
 * З екземпляра класу URL отримуємо екземпляр класу java.net.HttpUrlConnection.
 * У екземпляра класу HttpUrlConnection вказується HTTP метод, також у нього можна отримати статус (код та повідомлення),
 * отримати http header за індексом (getHeaderField(int n)) тощо.
 * Для того щоб надіслати POST запит, у якого є body, необхідно встановити setDoOutput(true), після чого можна передати
 * тіло запиту в OutputStream.
 * Щоб встановити значення header, необхідно скористатися методом setRequestProperty(String, String).
 * Наприклад, connection.setRequestProperty("Content-Type", "application/json");
 */
