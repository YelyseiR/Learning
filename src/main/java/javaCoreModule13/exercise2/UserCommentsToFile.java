package javaCoreModule13.exercise2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UserCommentsToFile {

    public static String url = "https://jsonplaceholder.typicode.com";


    public void sendCommentsToFile(int userId) throws IOException, URISyntaxException, InterruptedException {
        // Отримуємо ід останнього поста користувача
        int idForLastPost = idLastPost(userId);

        // Встановлюємо шлях до файлу за допомогою відносного шляху
        File file = new File("src/main/java/javaCoreModule13/exercise2/user-" + userId + "-post-" + idForLastPost + "-comments.json");

        // Створюємо FileWriter для запису у файл
        FileWriter fw = new FileWriter(file);

        // Записуємо всі коментарі у файл
        fw.write(getAllCommentsForSelectedUser(userId));

        // Закриваємо FileWriter
        fw.close();
        System.out.println("\n--- JSON FILE SUCCESSFULLY CREATED ---"); //виводимо у консоль у разі створення файлу
    }

    public String getAllCommentsForSelectedUser(int userId) throws IOException, URISyntaxException, InterruptedException {
        // Отримуємо ід останнього поста користувача
        int idForLastPost = idLastPost(userId);

        // Встановлюємо запит на отримання всіх коментарів для останнього поста користувача
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url + "/posts/" + idForLastPost + "/comments"))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        // Відправляємо запит і отримуємо відповідь з сервера
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Повертаємо тіло відповіді як рядок
        return httpResponse.body();
    }

    public int idLastPost(int userId) throws IOException, URISyntaxException, InterruptedException {
        // Отримуємо список всіх постів користувача
        List<Post> posts = getAllPosts(userId);

        // Знаходимо максимальний ід серед всіх постів
        return posts.stream()
                .mapToInt(Post::getId)
                .max()
                .getAsInt();
    }

    public List<Post> getAllPosts(int userId) throws IOException, InterruptedException, URISyntaxException {

        // Встановлюємо запит на отримання всіх постів користувача
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url + "/users/" + userId + "/posts"))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        // Відправляємо запит і отримуємо відповідь з сервера
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Приводимо тіло відповіді до List<Post>
        Type type = TypeToken.getParameterized(List.class, Post.class).getType();
        List<Post> result = new Gson().fromJson(httpResponse.body(), type);

        // Повертаємо список постів
        return result;
    }


    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        UserCommentsToFile userCommentsToFile = new UserCommentsToFile();

        // Виводимо всі коментарі для користувача з ід 1
        System.out.println(userCommentsToFile.getAllCommentsForSelectedUser(1));

        // Записуємо всі коментарі для користувача з ід 1 у файл
        userCommentsToFile.sendCommentsToFile(1);
    }
}
