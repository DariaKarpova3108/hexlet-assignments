package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    private String word;

    public ReversedSequence(String word) {
        this.word = word;
    }

    @Override
    public int length() {
        return word.length();
    }

    @Override
    public char charAt(int index) {
        return word.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        String subWord = word.substring(start, end);
        String reversWord = "";
        for (int i = subWord.length()-1; i >= 0; i--) {
            reversWord += word.charAt(i) + "";
        }
        return reversWord;
    }

    @Override
    public String toString() {
        String reversWord = "";
        for (int i = word.length()-1; i >= 0; i--) {
            reversWord += word.charAt(i) + "";
        }

        return reversWord;
    }
}

// END
