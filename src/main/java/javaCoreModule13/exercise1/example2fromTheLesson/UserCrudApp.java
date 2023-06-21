package javaCoreModule13.exercise1.example2fromTheLesson;
/**
 * Програма, що взаємодіє з API https://jsonplaceholder.typicode.com.
 * CRUD (Create, Read, Update, and Delete)  CRUD refers to the four basic operations a software application should
 * be able to perform – Create, Read, Update, and Delete. In such apps, users must be able to create data, have access
 * to the data in the UI by reading the data, update or edit the data, and delete the data.
 * C - create - POST
 * R - read - GET
 * U - update - PUT
 * D - delete - DELETE
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javaCoreModule13.exercise1.example2fromTheLesson.userStructure.UserEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;


public class UserCrudApp {

    // Визначаємо базову URL адресу для взаємодії з API
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    // Створюємо об'єкт Gson для роботи з JSON
    private static final Gson gson = new Gson();

    /**
     * Creates a new user entity by sending a POST request to the API.
     *
     * @return The created user entity.
     */
    public static UserEntity createUser() {
        try {
            URL url = new URL(BASE_URL);     // створили юрл об'єкт
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //здійснили конекшн до юрл
            connection.setRequestMethod("POST"); //задали http метод
            connection.setRequestProperty("Content-Type", "application/json"); //задали хедери (в цьому випадку працюємо с json об'єктом
            connection.setDoOutput(true); // у set output проставляємо чи очікуємо ми якись response body

            //Тіло нового користувача
            String requestBody = "{\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Vasyl Bebra\",\n" +
                    "    \"username\": \"Bebra\",\n" +
                    "    \"email\": \"bebra@.com\",\n" +
                    "    \"address\": {\n" +
                    "      \"street\": \"Khreschatyk\",\n" +
                    "      \"suite\": \"Apt. 777\",\n" +
                    "      \"city\": \"Kyiv\",\n" +
                    "      \"zipcode\": \"92998-3874\",\n" +
                    "      \"geo\": {\n" +
                    "        \"lat\": \"50.449365002409486\",\n" +
                    "        \"lng\": \"30.523878336473537\"\n" +
                    "      }\n" +
                    "    },\n" +
                    "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                    "    \"website\": \"bebra.org\",\n" +
                    "    \"company\": {\n" +
                    "      \"name\": \"Vasyl Bebra\",\n" +
                    "      \"catchPhrase\": \"Smell the bebra\",\n" +
                    "      \"bs\": \"harness real-time e-markets\"\n" +
                    "    }\n" +
                    "  }";

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBody.getBytes());  //в outputStream записали requestBody та конвертнули його в потік байтів
                outputStream.flush(); // flush надсилає дані
            }

            int responseCode = connection.getResponseCode(); // очікуємо респонс код
            if (HttpURLConnection.HTTP_CREATED == responseCode) { //записуємо константи завжди першими
                return getUserEntity(connection); //якщо отримали статус created (201), то переходимо в метод getUserEntity
                //
            }
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException("Something goes wrong");
        }
        return null;
    }

    /**
     * Updates an existing user entity by sending a PUT request to the API.
     *
     * @param id The ID of the user entity to update.
     * @return The updated user entity.
     */
    public static UserEntity updateUser(int id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT"); //прописуємо PUT для того щоб використати апдейт
            connection.setRequestProperty("Content-Type", "application/json"); //headers
            connection.setDoOutput(true); // у set output проставляємо чи очікуємо ми якись response body

            String requestBody = "{\"id\":5,\"name\":\"Bjorn Ironside\",\"username\":\"ironside\",\"email\":\"ragnarsson@example.com\"}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBody.getBytes());
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                return getUserEntity(connection);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Deletes a user entity by sending a DELETE request to the API.
     *
     * @param id The ID of the user entity to delete.
     * @return {@code true} if the deletion is successful, {@code false} otherwise.
     */
    public static boolean deleteUser(int id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Retrieves a list of all user entities from the API.
     *
     * @return A list of user entities.
     */
    public static List<UserEntity> getAllUsers() {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Type userListType = new TypeToken<List<UserEntity>>() {
                    }.getType();
                    return gson.fromJson(response.toString(), userListType);
                }
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves a user entity by its ID from the API.
     *
     * @param id The ID of the user entity to retrieve.
     * @return An optional containing the user entity if found, or empty optional if not found.
     */
    public static Optional<UserEntity> getUserById(int id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    UserEntity user = gson.fromJson(response.toString(), UserEntity.class);
                    return Optional.ofNullable(user);
                    /**
                     * Optional.ofNullable(user) використовується для створення об'єкту Optional, який може містити
                     * значення типу UserEntity або бути пустим (null). Це забезпечує безпеку та завершеність при обробці
                     * значення, яке може бути нульовим. Якщо user дорівнює null, то Optional буде порожнім, інакше він
                     * міститиме значення user. Це дозволяє забезпечити безпеку використання user, оскільки з Optional
                     * ми можемо визначити, чи є значення вказаного типу наявним, і забезпечити необхідну логіку в разі
                     * відсутності значення.
                     */
                }
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * Retrieves a user entity by its username from the API.
     *
     * @param username The username of the user entity to retrieve.
     * @return An optional containing the user entity if found, or empty optional if not found.
     */
    public static Optional<UserEntity> getUserByUsername(String username) {
        try {
            URL url = new URL(BASE_URL + "?username=" + username); //query parameter / параметр пошуку
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Type userListType = new TypeToken<List<UserEntity>>() {
                    }.getType();
                    List<UserEntity> users = gson.fromJson(response.toString(), userListType);
                    if (!users.isEmpty()) {
                        return Optional.of(users.get(0));
                    }
                }
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private static UserEntity getUserEntity(HttpURLConnection connection) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            //  http url connection після того як здійснили запит отримує певну відповідь - вона записується в наш інпут стрім
            //
            String line;
            StringBuilder response = new StringBuilder(); //записали все у стрінгбілдер
            while ((line = reader.readLine()) != null) {
                response.append(line); //допоки наш readLine не буде null ми додаєм у стрінгБілдер частини
                //наш json
            }
            return gson.fromJson(response.toString(), UserEntity.class);
        }
    }
}