public class Extraction extends HashFunction {
    public Extraction(int n) {
        num = n;
    }

    @Override
    public int hash(String input) {
        String numString = stringToNum(input);
        int size = numString.length();
        if (size > num) {
            String last = "";
            for (int i = size - 1, j = 0; j < num; i--, j++) {
                last = numString.charAt(i) + last;
            }
            numString = last;
        }
        return Integer.parseInt(numString);
    }

}
