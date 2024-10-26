package exercise;

class SafetyList {
    // BEGIN
    private int[] numbers = new int[8000];
    private int count = 0;

    public void add(int num) {
        synchronized (this) {
            if (count < numbers.length) {
                numbers[count] = num;
                count++;
            }
        }
    }

    public int get(int index) {
        return numbers[index];
    }

    public long getSize() {
        return count;
    }
    // END
}
