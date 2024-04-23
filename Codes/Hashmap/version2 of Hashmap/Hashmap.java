import java.lang.Math;;

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
        while(prime.currentPrime() < currentPrime) prime.nextPrime();

        array = new KeyValuePair[parts.length - 1];
        int i = 1;
        for(;i<parts.length;i+=1){
            if(!parts[i].equals("-")) array[i-1] = KeyValuePair.readString(parts[i]);
            else array[i-1] = null;
        }
    }

    public int hash(int studentNumber) {
        
        int hashVal = 0;
        String key =  Integer.toString(studentNumber);
        int tableSize = array.length;
        for (int i = 0; i < key.length(); i++)
            hashVal = prime.currentPrime() * hashVal + Character.getNumericValue(key.charAt(i));

        hashVal %= tableSize;
        return Math.abs(hashVal);
    }

    public KeyValuePair search(int studentNumber) {
        int hash = hash(studentNumber);
        if(array[hash] != null && array[hash].studentNumber == studentNumber){
            return array[hash];
        }
        else
            {
               int offset = 1;
               for(; offset <= 3; offset+=1){
                int index = Math.abs(hash + offset * offset * prime.currentPrime()) % array.length;
                if(array[index] != null && array[index].studentNumber == studentNumber) return array[index];

                index = Math.abs(hash - offset * offset * prime.currentPrime()) % array.length;
                if(array[index] != null && array[index].studentNumber == studentNumber) return array[index];
               }
            }
           return null;
    }
    

    public void insert(int studentNumber, int mark) {
        KeyValuePair existingPair = search(studentNumber);
    
        if (existingPair != null) {
            existingPair.mark = mark;
            return;
        } 
        else
            {
                int hash = hash(studentNumber);
                if(array[hash] == null){
                    array[hash] = new KeyValuePair(studentNumber, mark);
                    return;
                } 
                else {
                    int offset = 1;
                    for(; offset <= 3; offset+=1){
    
                        int index = Math.abs(hash + offset * offset * prime.currentPrime()) % array.length;
                        if(array[index] == null) {
                            array[index] = new KeyValuePair(studentNumber, mark);
                            return;
                        }
    
                        index = Math.abs(hash - offset * offset * prime.currentPrime()) % array.length;
                        if(array[index] == null){
                            array[index] = new KeyValuePair(studentNumber, mark);
                            return;
                        }
                    }
                }
            }
            rehash();
            insert(studentNumber, mark);
    }
        
    private void rehash() {
        KeyValuePair[] oldArray = array;
        array = new KeyValuePair[oldArray.length * 2];
        prime.nextPrime();
        
        for(int i = 0; i < oldArray.length; i++){
            if(oldArray[i] != null){
                insert(oldArray[i].studentNumber, oldArray[i].mark);
            }
        }
    }

    public void remove(int studentNumber) {
        int hash = hash(studentNumber);
        if(array[hash] != null && array[hash].studentNumber == studentNumber){
            array[hash] = null;
        } else {
            int offset = 1;
            for(; offset <= 3; offset+=1){
                int index = Math.abs(hash + offset * offset * prime.currentPrime()) % array.length;
                if(array[index] != null && array[index].studentNumber == studentNumber) {
                    array[index] = null;
                    return;
                }

                index = Math.abs(hash - offset * offset * prime.currentPrime()) % array.length;
                if(array[index] != null && array[index].studentNumber == studentNumber){
                    array[index] = null;
                    return;
                }
            }
        }
    }   
}
