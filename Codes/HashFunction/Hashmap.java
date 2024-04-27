public class Hashmap {
    public HashFunction[] functions;
    private String[] map;

    public Hashmap(int length, HashFunction[] funcs) {
        map = new String[length];
        for (int i = 0; i < length; i++) {
            map[i] = "";
        }
        functions = funcs;
    }

    public int hash(String val) {
        int sum = 0;
        for (int i = 0; i < map.length; i++) {
            sum = sum + functions[i].hash(val);
        }
        sum = sum % map.length;
        return sum;
    }

    public boolean contains(String val) {
        int sum = 0;
        for (int i = 0; i < functions.length; i++) {
            sum = sum + functions[i].hash(val);
        }
        sum = sum % map.length;
        if (!map[sum].equals("")) {
            String[] values = map[sum].split("&/");
            for (int i = 0; i < values.length; i++) {
                if (values[i].equals(val)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void insert(String s) {
        if (contains(s) == true) {
            return;
        }
        int sum = 0;
        for (int i = 0; i < functions.length; i++) {
            sum = sum + functions[i].hash(s);
        }
        sum = sum % map.length;
        if (!map[sum].equals("")) {
            map[sum] += "&/" + s;
        } else {
            map[sum] = s;
        }
    }

    public void remove(String s) {
        int sum = 0;
        for (int i = 0; i < functions.length; i++) {
            sum = sum + functions[i].hash(s);
        }
        sum = sum % map.length;
        if (!map[sum].equals("")) {
            String tmp = "";
            Boolean flag = false;
            String[] values = map[sum].split("&/");
            for (int i = 0; i < values.length; i++) {
                if (values[i].equals(s)) {
                    continue;
                }
                if (!flag) {
                    flag = true;
                    tmp = values[i];
                } else {
                    tmp += "&/" + values[i];
                }

            }
            map[sum] = tmp;
        }
    }

    public String toString() {
        String tostring = "";
        tostring += "[";
        for (int k = 0; k < map.length - 1; k++) {
            String tmp = "";
            Boolean flag = false;
            String[] values = map[k].split("&/");
            for (int i = 0; i < values.length; i++) {

                if (!flag) {
                    flag = true;
                    tmp = values[i];
                } else {
                    tmp += "," + values[i];
                }

            }
            tostring = tostring + tmp + ";";
        }
        if (map.length > 0) {
            // tostring += map[map.length - 1];

            String tmp = "";
            Boolean flag = false;
            String[] values = map[map.length - 1].split("&/");
            for (int i = 0; i < values.length; i++) {

                if (!flag) {
                    flag = true;
                    tmp = values[i];
                } else {
                    tmp += "," + values[i];
                }

            }
            tostring = tostring + tmp;
        }
        tostring += "]";
        return tostring;
    }
};
