package javaCoreModule11.exercise4;

public class LinearCongruentGeneratorExample {
    private static long[] LCG(int noOFNums, long seed, long modulus, long amplifier, long increment){
        long[] randomNums = new long[noOFNums];
        randomNums[0] = seed;

        for (int i = 1; i < noOFNums; i++) {
            randomNums[i] = (amplifier * randomNums[i - 1] + increment) % modulus;
        }
        return randomNums;
    }

    public static void main(String[] args) {
        long[] myRandomNums = LCG(10, 3, 5, 3, 2);
        for(long i : myRandomNums)
            System.out.println(i);
    }
}

//YOUTUBE Example https://www.youtube.com/watch?v=zBX8_OIRhCs
