package javaCoreModule11.exercise5;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Метод public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) який "перемішує"
 * елементи зі стрімів first та second, зупиняючись тоді, коли у одного зі стрімів закінчаться елементи.
 */
@SuppressWarnings("unchecked")
public class MixStreamElements {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) { //створюємо метод з дженеріками, що приймає два стріми
        T[] firstArray = (T[])first.toArray(); //мамсив першого стріму
        T[] secondArray = (T[])second.toArray(); // масив другого стріму
        List<T> list = new LinkedList<T>(); //створюємо список з елементами стрімів
        for (int i = 0; i < Math.min(firstArray.length, secondArray.length); i++) { //проходимось по мінімальній довжині масивів
            list.add((T)firstArray[i]); //додаємо елемент з першого масиву в список
            list.add((T)secondArray[i]); //додаємо елемент з другого масиву в список
        }
        return list.stream(); //повертаємо стрім зі списку
    }

    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("1", "2", "3", "4", "5", "6");  //створюємо стрім з першими елементами
        Stream<String> stream2 = Stream.of("value 1", "value 2", "value 3"); //створюємо стрім з другими елементами
        Stream<String> mixed = zip(stream1, stream2); //міксуємо перший і другий стріми та зберігаємо результат у змінну
        mixed.forEach(line->System.out.println(line)); // лямбда вираз, який друкує кожен елемент стріму в консоль
    }
}

/**
 * Анотація @SuppressWarnings("unchecked") це інструкція компілятору про те, що він має ігнорувати попередження
 * про невизначеність типу T, який ми отримуємо після перетворення з Object. Цю анотацію рекомендується
 * використовувати тільки у випадках, коли відомо, що перетворення є допустимим і не приведе до помилки виконання
 * програми. У інших випадках краще використовувати інші способи роботи з типами даних, щоб уникнути можливих помилок.
 */