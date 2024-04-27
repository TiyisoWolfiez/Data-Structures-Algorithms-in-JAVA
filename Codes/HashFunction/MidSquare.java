public class MidSquare extends HashFunction {

    public MidSquare(int n) {
        num = n;
    }

    public int hash(String input) {
        String numString = stringToNum(input);
        long number = Long.parseLong(numString);
        number = number * number;
        numString = Long.toString(number);
        int size = numString.length();
        if (size > num) {
            int odd = (size - num) % 2;
            if (odd != 0) {
                numString = numString + "0";
                size = numString.length();
            }
            odd = (size - num) / 2;
            numString = numString.substring(odd);
            String finalv = "";
            for (int i = 0; i < num; i++) {
                finalv += numString.charAt(i);
            }
            return Integer.parseInt(finalv);
        }
        return Integer.parseInt(numString);
    }

}
