import java.lang.Math;

public class Hashmap {
    public KeyValuePair[] array;
    public PrimeNumberGenerator prime = new PrimeNumberGenerator();

    @Override
    public String toString() {
        String res = String.valueOf(prime.currentPrime()) + "\n";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += "\n";
            }
            res += i + "\t";
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res;
    }

    public String toStringOneLine() {
        String res = String.valueOf(prime.currentPrime()) + "[";
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                res += ",";
            }
            if (array[i] == null) {
                res += "-";
            } else {
                res += array[i].toString();
            }
        }
        return res + "]";
    }

    public Hashmap() {
        array = new KeyValuePair[1];
    }

    public Hashmap(String inp) {
        String[] parts = inp.split("\\[|\\]|,");
        int currentPrime = Integer.parseInt(parts[0]);

        prime = new PrimeNumberGenerator();
        while(prime.currentPrime() < currentPrime){
            prime.nextPrime();
        }

        array = new KeyValuePair[parts.length - 1];

        int k = 1;
        while(k < parts.length){
            if(!parts[k].equals("-")) {
                array[k-1] = KeyValuePair.fromString(parts[k]);
            }
            else {
                array[k-1] = null;
            }
            k += 1;
        }
    }

    public int hash(int studentNumber) {
        int hashVal = 0;
        int currentPrime = prime.currentPrime();
        int tableSize = array.length;

        String studentNumberStr = Integer.toString(studentNumber);
        for (int i = 0; i < studentNumberStr.length(); i++) {
            int digit = Character.getNumericValue(studentNumberStr.charAt(i));
            hashVal = currentPrime * hashVal + digit;
        }
        
        hashVal %= tableSize;
        
        return Math.abs(hashVal);
    }

    public KeyValuePair search(int studentNumber) {
        int hashSN = hash(studentNumber);
        
        if (array[hashSN] != null && array[hashSN].studentNumber == studentNumber) {
            return array[hashSN];
        } else {
            int currentPrime = prime.currentPrime();
            int arrLen = array.length;
            int offset = 1;
            while(offset <= 3) {
                int index = Math.abs(hashSN + (offset * offset) * currentPrime) % arrLen;
                if (array[index] != null && array[index].studentNumber == studentNumber) {
                    return array[index];
                }
    
                index = Math.abs(hashSN - (offset * offset) * currentPrime) % arrLen;
                if (array[index] != null && array[index].studentNumber == studentNumber) {
                    return array[index];
                }
                offset += 1;
            }
        }

        return null;
    }
    

    public void insert(int studentNumber, int mark) {
        KeyValuePair alreadyInPair = search(studentNumber);

        if (alreadyInPair != null && alreadyInPair.studentNumber == studentNumber) {
            alreadyInPair.mark = mark;
            return;
        } else {
            int hashSN = hash(studentNumber);
            
            if (array[hashSN] == null) {
                array[hashSN] = new KeyValuePair(studentNumber, mark);
                return;
            } else {
                int offset = 1;
                int currentPrime = prime.currentPrime();
                int arrLen = array.length;
                while (offset <= 3) { 
                    int index = Math.abs(hashSN + (offset * offset) * currentPrime) % arrLen;
                    if (array[index] == null) {
                        array[index] = new KeyValuePair(studentNumber, mark);
                        return;
                    }

                    index = Math.abs(hashSN - (offset * offset) * currentPrime) % arrLen;
                    if (array[index] == null) {
                        array[index] = new KeyValuePair(studentNumber, mark);
                        return;
                    }
                    offset += 1;
                }
            }
        }
        
        rehash();
        insert(studentNumber, mark);
    }
    
    int size() {
        return array.length;
    }

    private void rehash() {
        KeyValuePair[] oldArr = array;
        array = new KeyValuePair[oldArr.length * 2];
        prime.nextPrime();
    
        for(KeyValuePair pair : oldArr){
            if(pair != null){
                insert(pair.studentNumber, pair.mark);
            }
        }
    }

    public void remove(int studentNumber) {
        int hashSN = hash(studentNumber);
        
        if (array[hashSN] != null && array[hashSN].studentNumber == studentNumber) {
            array[hashSN] = null;
        } else {
            int currentPrime = prime.currentPrime();
            int arrLen = array.length;
            int offset = 1;
            while(offset <= 3) {
                int index = Math.abs(hashSN + (offset * offset) * currentPrime) % arrLen;
                if (array[index] != null && array[index].studentNumber == studentNumber) {
                    array[index] = null;
                    return;
                }
    
                index = Math.abs(hashSN - (offset * offset) * currentPrime) % arrLen;
                if (array[index] != null && array[index].studentNumber == studentNumber) {
                    array[index] = null;
                    return;
                }
                offset += 1;
            }
        }
    }
}
