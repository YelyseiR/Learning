package javaCoreModule12.exercise2.fizzBuzzExamplesAndExplanations;

import java.util.function.IntConsumer;

class FizzBuzzExample {
    private int n;
    private int i;

    public FizzBuzzExample(int n) {
        this.n = n;
        this.i = 1;
    }

    // printFizz.run() outputs "fizz".
    public void fizzExample(Runnable printFizz) throws InterruptedException {
        synchronized (this) {
            while (i<=n) {
                if (i%3==0 && i%5!=0) {
                    printFizz.run();
                    i+=1;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzzExample(Runnable printBuzz) throws InterruptedException {
        synchronized (this) {
            while (i<=n) {
                if (i%3!=0 && i%5==0) {
                    printBuzz.run();
                    i+=1;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzzExample(Runnable printFizzBuzz) throws InterruptedException {
        synchronized (this) {
            while (i<=n) {
                if (i%15==0) {
                    printFizzBuzz.run();
                    i+=1;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized (this) {
            while (i<=n) {
                if (i%3!=0 && i%5!=0) {
                    printNumber.accept(i);
                    i+=1;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FizzBuzzExample fizzBuzz = new FizzBuzzExample(15);

        Thread fizzThread = new Thread(() -> {
            try {
                fizzBuzz.fizzExample(() -> System.out.print("fizz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread buzzThread = new Thread(() -> {
            try {
                fizzBuzz.buzzExample(() -> System.out.print("buzz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread fizzBuzzThread = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzzExample(() -> System.out.print("fizzbuzz "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread numThread = new Thread(() -> {
            try {
                fizzBuzz.number(n -> System.out.print(n + " "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        numThread.start();

        fizzThread.join();
        buzzThread.join();
        fizzBuzzThread.join();
        numThread.join();
    }
}

/**
 * https://leetcode.com/problems/fizz-buzz-multithreaded/solutions/3084050/java-solutions-with-explanation-approach-1-object-lock-approach-2-semaphore/
 */

