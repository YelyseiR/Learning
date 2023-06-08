package javaCoreModule12.examplesWithTimers;

public class ProgrammExecutionTime {
    public void display() {
        System.out.println("Calculating method execution time: ");
    }

    public static void main(String[] args) {
        ProgrammExecutionTime obj = new ProgrammExecutionTime();
        long start = System.nanoTime();

        obj.display();

        long end = System.nanoTime();

        long execution = end - start;
        System.out.println("Execution time: " + execution + " nanoseconds");
    }
}
