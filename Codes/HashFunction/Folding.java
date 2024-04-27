public class Folding extends HashFunction {
    public boolean shift;

    public Folding(int n, boolean s) {
        shift = s;
        num = n;
    }

    @Override
    public int hash(String input) {
        String numString = stringToNum(input);
        int sum = 0;
        for (int size = numString.length(); size % num != 0; size = numString.length()) {
            numString = numString + "0";
        }

        int odd = 1;
        while (numString.length() > 0) {
            String tmp = numString.substring(0, Math.min(num, numString.length()));
            if (!shift && odd % 2 == 0) {
                String revtmp = "";
                int i = 0;
                while (i < tmp.length()) {
                    revtmp = tmp.charAt(i) + revtmp;
                    i++;
                }
                tmp = revtmp;
            }
            odd++;
            sum = sum + Integer.parseInt(tmp);
            numString = numString.substring(Math.min(num, numString.length()));
        }
        return sum;
    }
}
