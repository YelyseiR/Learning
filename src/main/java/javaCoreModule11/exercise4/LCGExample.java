package javaCoreModule11.exercise4;

import java.util.stream.LongStream;

public class LCGExample {
    private long seed;
    private long a;
    private long c;
    private long m;

    LCGExample(long seed, long a, long c, long m) {
        this.seed = seed;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public LongStream rand() {
        return LongStream.iterate(seed, x -> (a * x + c) % m).skip(1);
    }

    void setSeed(long seed) {
        this.seed = seed;
    }

    long getSeed() {
        return seed;
    }

    public static void main(String[] args) {
        LCGExample generator = new LCGExample(1L, 1103515245L, 12345L, (long) Math.pow(2, 31));
        System.out.println("First 5 random numbers:");
        generator.rand().limit(5).forEach(System.out::println);

        generator.setSeed(12345);
        System.out.println("\nNext 5 random numbers with new seed:");
        generator.rand().limit(5).forEach(System.out::println);
    }
}
