public class Division extends HashFunction {

    public Division(int n) {
        num = n;
    }

    @Override
    public int hash(String input) {
        String numString = stringToNum(input);
        long number = Long.parseLong(numString);
        long finalv = number % num;
        return (int) finalv;
    }
}
