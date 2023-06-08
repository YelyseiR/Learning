package javaCoreModule12.exercise1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerTracker {
    public static void main(String[] args) {
        //first thread which shows runtime of program every second
        Thread timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // проставляємо необхідний формат відображення часу
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                //видаємо повідомлення про початок програми
                System.out.println("The program has been started at " + formatter.format(new Date()));
                while (true) {
                    //виводоми поточний час у форматі який вказаний вище
                    System.out.println("It`s going " + formatter.format(new Date()));
                    try {
                        Thread.sleep(1000);//кидаємо потік у сліп на 1 сек (або 1000 мілісек)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        timeThread.start(); //запуск необхідного потоку

        //second thread which shows program runtime every 5 seconds
        Thread fiveSecThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0; // додажмо лічильник
                while (true) {
                    if(count > 0 && count% 5 == 0) { //якщо лічильник дорівнює 5, то виводимо повідомлення що минуло 5 сек
                        System.out.println("5 seconds passed");
                    }
                    count++;
                    try {
                        Thread.sleep(1000); //також продовжуємо кидати потік у сліп на 1 сек
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        fiveSecThread.start(); //запуск необхідного потоку

        //потік завершення програми. У разі зупинки програми - виводиться повідомлення про її завершення
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            @Override
            public void run() {
                System.out.println("The program is finished at " + formatter.format(new Date()));
            }
        }));
    }
}


