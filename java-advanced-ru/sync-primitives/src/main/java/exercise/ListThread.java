package exercise;

import java.util.Random;

// BEGIN
public class ListThread extends Thread {
    private SafetyList list;
    private Random random = new Random();

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            list.add(random.nextInt());
        }

    }
}
// END
