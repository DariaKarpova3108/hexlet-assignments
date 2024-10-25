package exercise;

// BEGIN
public class MinThread extends Thread {
    private int[] numbers;
    private int min = Integer.MAX_VALUE;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        var nameThread = MinThread.currentThread().getName();
        System.out.println("(MIN) " + nameThread + " is started");
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        System.out.println("(MIN) " + nameThread + " is finished");
    }

    public int getMin() {
        return min;
    }
}
// END
