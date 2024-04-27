public abstract class HashFunction {
    public abstract int hash(String input);

    public int num;

    public String stringToNum(String input) {
        int[] vals = new int[6];

        int i = 0;
        for (Character c : input.toCharArray()) {
            vals[i++ % 6] += c;
        }

        String[] stringVals = new String[6];

        i = 0;
        for (int v : vals) {
            stringVals[i] = String.format("%3d", v).replace(" ", "0");
            while (stringVals[i].length() > 3) {
                stringVals[i] = stringVals[i].substring(1);
            }
            i++;
        }

        String concat = "";
        for (int j = 5; j >= 0; j--) {
            concat += stringVals[j];
        }

        while (concat.length() > 1 && concat.charAt(0) == '0') {
            concat = concat.substring(1);
        }

        return concat;
    }
}
