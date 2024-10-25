package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static void main(String[] args) {
        int[] numbers = {10, -4, 67, 100, -100, 8};
        var result = getMinMax(numbers);
        System.out.println(result);
    }


    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new HashMap<>();
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            minThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int max = maxThread.getMax();
        int min = minThread.getMin();

        result.put("min", min);
        result.put("max", max);
        return result;
    }
    // END
}
