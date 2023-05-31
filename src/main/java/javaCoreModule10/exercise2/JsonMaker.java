package javaCoreModule10.exercise2;

import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonMaker {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>(); //створення порожнього списку користувачів
        File input = new File("src/main/java/javaCoreModule10/Exercise2/file.txt"); //створення об'єкту для читання з файлу
        File output = new File("src/main/java/javaCoreModule10/Exercise2/file.json"); //створення об'єкту для запису до файлу
        try (Scanner scanner = new Scanner(input); FileWriter fileWriter = new FileWriter(output)) {
            //створення нових об'єктів для читання та запису, які автоматично закриваються після виконання за допомогою try\catch
            String str = scanner.nextLine().trim(); //читання першого рядка з файлу
            while (scanner.hasNext()) { //проходження по всіх рядках файлу
                str = scanner.nextLine().trim(); //читання наступного рядка з файлу
                int num = str.indexOf(" "); //знаходження індексу пробіла
                //створення нового об'єкту користувача та додавання його до списку
                users.add(new User(str.substring(0, num), Integer.parseInt(str.substring(num + 1))));
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create(); //створення об'єкту Gson з форматуванням виводу
            JsonElement jsonTree = gson.toJsonTree(users); //створення дерева JSON зі списку користувачів
            gson.toJson(jsonTree, fileWriter); //запис дерева у вихідний файл
        } catch (IOException e) { //обробка можливих ексепшинів
            // викидаємо ексепшн якщо не вдається запарсити файл
            System.out.println("Error while parsing file: " + e.getMessage());
        }
        System.out.println("The JSON file successfully created!"); //виведення повідомлення про успішне створення файлу
    }
}

/**
 JSON (JavaScript Object Notation) - це легкий формат обміну даними, який використовується для передачі структурованих
 даних через інтернет. Він був створений для спрощення передачі даних при взаємодії між веб-сайтами та додатками.
 JSON представляє собою текстовий формат, він легко перетворюється в структури даних, такі як масиви та об'єкти в більшості мов
 програмування. JSON має простий синтаксис та зазвичай має менший обсяг даних порівняно з іншими форматами, такими як XML.
 JSON підтримується більшістю мов програмування та використовується для зберігання та передачі даних у веб-програмуванні
 та розробці додатків.
 Веб-сервера найчастіше використовують JSON для запитів-відповідей під час реалізації своїх API.
 Налаштування застосунків, веб сеансів і т.д. часто зберігаються в JSON.

 GSON є бібліотекою Java для роботи з JSON, яка дозволяє серіалізувати та десеріалізувати дані JSON з об'єктами Java.
 Основна різниця між JSON та GSON полягає у тому, що JSON є форматом даних, тоді як GSON є інструментом для роботи з цим
 форматом даних в Java. Використання GSON дозволяє зменшити кількість коду, який необхідно написати для роботи з JSON,
 та ефективніше працювати з даними.
 */


