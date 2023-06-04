package javaCoreModule11.exercise3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortArray {
    public static void main(String[] args) {
        String [] array = new String[]{"1, 2, 0", "4, 5"};
        // Конвертація масиву у список, щоб використати Stream API
        List<Integer> sortedArray = Arrays.asList(array)
                .stream()
                .flatMap(s -> Arrays.stream(s.split(", ")))
                // Кожен символ-число конвертуємо в Integer
                .map(Integer::parseInt)
                // Сортуємо числа по зростанню
                .sorted()
                // Збираємо всі числа в список
                .collect(Collectors.toList());
        System.out.println(sortedArray);
    }
}
