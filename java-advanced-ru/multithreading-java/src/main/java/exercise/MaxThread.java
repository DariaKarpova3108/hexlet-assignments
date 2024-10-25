package exercise;

// BEGIN
public class MaxThread extends Thread {
    private int[] arrays;
    private int max = Integer.MIN_VALUE;

    public MaxThread(int[] arrays) {
        this.arrays = arrays;
    }

    @Override
    public void run() {
       var nameThread =  MaxThread.currentThread().getName();
        System.out.println(nameThread + " is started (MAX)");
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] > max) {
                max = arrays[i];
            }
        }
        System.out.println(nameThread + " is finished (MAX)");
    }

    public int getMax() {
        return max;
    }
}
// END
