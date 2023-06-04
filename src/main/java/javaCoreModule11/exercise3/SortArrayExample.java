package javaCoreModule11.exercise3;

import java.util.Arrays;

public class SortArrayExample {
    public static void main(String[] args) {
        String[] arr = { "1, 2, 0", "4, 5" };
        String str = String.join(",", arr);

        String[] numbers = str.split(",\\s*");
        int[] array = new int[numbers.length];  // Створення масиву цілих чисел array

        for (int i = 0; i < numbers.length; i++) { // Перетворення підрядків масиву numbers в цілі числа та зберігання їх у масиві array
            array[i] = Integer.parseInt(numbers[i]);
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            if (i != array.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
