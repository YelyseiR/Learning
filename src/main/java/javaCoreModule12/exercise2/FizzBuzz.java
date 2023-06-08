package javaCoreModule12.exercise2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzz {
    private int n;
    private BlockingQueue<String> queue; //черга для виведення значень

    /**
     *BlockingQueue<String> queue - це інтерфейс, який реалізує чергу, в якій можна зберігати тільки рядки типу String.
     *  Ця черга реалізує механізм блокування, що дозволяє потоку, який намагається взяти елемент з порожньої черги,
     *  чекати на додавання елементу до черги. Коли черга заповнюється елементами, поток, який додає елемент до черги,
     *  також блокується, якщо черга вже заповнена. Цей механізм дозволяє управляти доступом до поточного стану черги
     *  та уникати переповнення або витоку пам'яті, що може статися при паралельному доступі до загального ресурсу.
     */

    public FizzBuzz(int n) { //конструктро для классу
        this.n = n;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void fizz() throws InterruptedException { //метод що виводить fizz якщо число ділиться на 3
        for (int i = 3; i <= n ; i+=3) {
            if(i % 5 == 0) {
                queue.put("fizz"); //додаємо до черги
            }
        }
    }

    public void buzz() throws InterruptedException { //метод що виводить buzz якщо число ділиться на 5
        for (int i = 5; i <= n ; i+=5) {
            if (i % 3 == 0) {
                queue.put("buzz");
            }
        }
    }

    public void fizzbuzz() throws InterruptedException { //метод що виводить fizzbuzz якщо число ділиться на 3 і на 5 одночасно
        for (int i = 15; i <= 15 ; i+= 15) {
            queue.put("fizzbuzz");
        }
    }

    public void number() throws InterruptedException { //метод number(), щоб вивести наступне число з черги, якщо є таке число для виведення.
        for (int i = 1; i <= n ; i++) {
            if(i % 3 !=0 & i % 5 != 0) {
                queue.put(Integer.toString(i));
            } else {
                System.out.println(queue.take());
            }
        }
    }
}
