package javaCoreModule12.examplesWithTimers;

public class Countdown {
    public static void main(String[] args) {
        System.out.println("==== Countdown from 10! ====");
        Thread countdownThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i >= 1; i--) {
                    System.out.print(i + " ");
                    try{
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                }
                System.out.println("\nFinished!");
            }
        });
        countdownThread.start();
    }
}

/**
 * Example from YouTube channel https://www.youtube.com/@TapTap_196/videos
 * https://www.youtube.com/shorts/ZWjfSSukdBI
 */