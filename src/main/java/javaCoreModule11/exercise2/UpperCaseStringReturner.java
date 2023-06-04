package javaCoreModule11.exercise2;
import javaCoreModule11.Names;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UpperCaseStringReturner { //Повертає список цих рядків у верхньому регістрі, і відсортованих за спаданням (від Z до A).
    public static void main(String[] args) {
        //Створення потоку зі списку об'єктів неймс
        final String result = namesList.stream()
                //повертаємо ім'я з кожного об'єкту неймс
                .map(Names::getName)
                .map(String::toUpperCase) //перетворюємо у верхній регістр
                .sorted(Comparator.reverseOrder()) // Використання методу sorted для сортування імен за порядком спадання (від Z до A)
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }
    public static List<Names> namesList = Arrays.asList(
            new Names(1, "Mykhailo"),
            new Names(2, "Bohdan"),
            new Names(3, "Roman"),
            new Names(4, "Viktor"),
            new Names(5, "Oleksiy"),
            new Names(6, "Olga"),
            new Names(7, "Viktor"),
            new Names(8, "Volodymyr"),
            new Names(9, "John"),
            new Names(10, "Mike"));
}

/**
 *  Метод .sorted(Comparator.reverseOrder()) використано для сортування імен за порядком спадання, тобто починаючи
 з останньої літери у алфавітному порядку.Метод reverseOrder() викликає статичний метод порівняння у класі Comparator,
 який повертає інстанцію класу порівняння за зворотнім порядком. Це означає, що при порівнянні двох елементів, порядок
 буде обернений, тобто елементи будуть впорядковані від останнього максимального елементу (якщо використовується порядок
 літер англійського алфавіту, то це буде буква Z) до першого мінімального елементу (буква A в англійському алфавіті).
 Таким чином, використання sorted(Comparator.reverseOrder()) забезпечує відсортування елементів за спаданням.
 У нашому коді це означає, що імена будуть відсортовані від великих літер до малих, в порядку Z до A.

 Метод .sorted(Comparator.reverseOrder()) можна використати на будь-якому списку або потоці даних, коли потрібно
 відсортувати елементи за спаданням. Наприклад, він може бути використаний для сортування чисел в масиві, рядків
 у тексті, або об'єктів класу за допомогою власного критерію порівняння. Також метод .sorted(Comparator.reverseOrder())
 може бути використаний у веб-програмуванні для відображення списку елементів на сторінці в зворотному порядку.
 Наприклад, якщо потрібно відобразити список повідомлень та відсортувати його за датою найновіші-найстаріші,
 то використання методу .sorted(Comparator.reverseOrder()) може допомогти досягти цього.
 */
