package javaCoreModule10.exercise1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {
    private static final String sourceFile = "src/main/java/javaCoreModule10/Exercise1/file.txt"; // відносний шлях до файлу
    public static void main(String[] args) {
        //зчитуємо файл за допомогою ФайлІнпутСтрім
        try(FileInputStream fileInputStream = new FileInputStream(sourceFile)) {
            Scanner scanner = new Scanner(fileInputStream);
            String line;
            // визначаємо шаблон для відповідних номерів телефонів
            Pattern pattern = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}");
            // проходимо по кожному рядку у файлі за допомгою вайл
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                // знаходимо номери телефонів з використанням визначеного шаблону за допомогою метчера
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    // перевіряємо чи є номер телефону валідним у файлі та виводимо його, якщо номер є валідним
                    if(isValidPhoneNumber(matcher.group())) {
                        System.out.println(matcher.group());
                    }
                }
            }
        }
        // додаємо ексепшн на випадок помилки зчитування файлу
        catch (IOException e) {
            System.out.println("Error while parsing the file: " + e.getMessage());
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) { // метод для безпосередньо перевірки валідності номерів
        // перевіряємо, чи відповідає переданий номер телефону необхідному шаблону
        if(phoneNumber.matches("\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}")) {
            return true;
        }
        return false;
    }
}
