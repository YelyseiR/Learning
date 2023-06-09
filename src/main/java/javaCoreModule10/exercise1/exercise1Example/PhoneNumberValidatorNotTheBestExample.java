package javaCoreModule10.exercise1.exercise1Example;

import java.io.BufferedReader; /** це клас, який призначений для забезпечення буферизованного читання текстових рядків
з символьних потоків вводу. Він дозволяє прочитати дані з потоку і зберегти їх в буфері, щоб укникнути частих операцій
вводу-виводу, що можуть доповнити роботу програми. Зазвичай клас БафферРідер виуористовується з символьними потоками
вводу, такими як ФайлРідер. Символьні потомки вводу відображають байти, що зчитуються в символи, які можна опрацювати.
Буферизація - це процес зберігання даних у буфері (та їх подальше опрацювання в цьому буфері) замість безпосередньої
 їх обробки. Буферизоване читання - це техніка, яка дозволяє читати дані з файлу (або іншого джерела), оброблюючи їх
 у буфері, щоб зменшити кількість взаємодій з файлом та збільшити продуктивність.
У джава буфер - це тимчасове місце для зберігання даних, що дозволяє значно підвищити ефективність роботи зі
введенням-виведенням даних. В Java є багато класів, які підтримують буферизацію, зокрема для читання і запису файлів,
мережевого введення-виведення та багато іншого.
*/
import java.io.FileReader; // це клас, який дозволяє читати з вказаного файла.
import java.io.IOException; // інпут/аутпут ексепш
import java.util.regex.Matcher; // це клас, який дозволяє здійснювати збіги тексту з регулярними виразами.
import java.util.regex.Pattern; /** це клас, який містить визначений регулярний вираз, який можна використовувати для
 здійснення збігів тексту. У даному коді, використовується метод "compile()" для визначення шаблону регулярного виразу
 на рядку тексту. Цей метод можна використовувати для виконання різних операцій з рядками, таких як перевірка
 відповідності рядків заданому регулярному виразу, розбиття рядків на підрядки за допомогою регулярного виразу, тощо **/
public class PhoneNumberValidatorNotTheBestExample {
    public static void main(String[] args) {
        // читаємо номери телефонів з файлу
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\yelis\\OneDrive\\Desktop\\file.txt"))) {
            String line;
            // визначаємо шаблон для відповідних номерів телефонів
            Pattern pattern = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}");
            // проходимо по кожному рядку у файлі за допомгою вайл
            while ((line = bufferedReader.readLine()) != null) {
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

    // метод для безпосередньо перевірки валідності номерів
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // перевіряємо, чи відповідає переданий номер телефону необхідному шаблону
        if(phoneNumber.matches("\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}")) {
            return true;
        }
        return false;
    }
}

/**
 Краще всього вказувати відносний шлях до файлу (наприклад, з коренню проекта, а не з папки безпосередньо на комп'ютері
 користувача), так як при заливанні коду на гіт-хаб інший користувач не буде мати доступу до файлу, якщо шлях до нього
 буде вказаний як папка на комп'ютері автора коду. (absolute path/relative path)
 (Наприклад, C:\\Users\\yelis\\OneDrive\\Desktop\\phone numbers.txt" - це є невірний шлях до файлу.
  А такий src/main/java/javaCoreModule10/phone numbers.txt - буде вірним, так як на гіті інший користувач буде мати
 доступ до файлу)
 */


