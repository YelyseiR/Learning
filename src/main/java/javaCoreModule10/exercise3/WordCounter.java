package javaCoreModule10.exercise3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        // задаємо шлях до необхідного файлу
        String path = String.valueOf(new File("src/main/java/javaCoreModule10/Exercise3/words"));
        // створюємо мапу, де зберігатимуться слова як ключі та їх кількість як значення
        Map<String, Integer> resultMap = new HashMap<>();
        // створюємо список, де зберігатимуться записи зі словами та їх кількістю, який можна буде сортувати за значенням
        List<Map.Entry<String, Integer>> list = new LinkedList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            // скануємо файл по словам
            while (scanner.hasNext()) {
                String s = scanner.next();
                // якщо слово вже було знайдено, то додавати до кількості + 1
                if (resultMap.containsKey(s)) {
                    resultMap.put(s, resultMap.get(s) + 1);
                    // якщо слово ще не зустрічалося, то додати його до мапи з кількістю 1
                } else {
                    resultMap.put(s, 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

        // додаємо у список записи зі словами та їх кількістю
        list.addAll(resultMap.entrySet());
        // сортуємо список за значенням кількості від більшого до меншого
        Collections.sort(list, ((o1, o2) -> o2.getValue() - o1.getValue()));

        // виводимо відсортований список у консоль
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getKey() + " " + list.get(i).getValue());
        }
    }
}
