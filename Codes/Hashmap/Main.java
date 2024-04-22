public class Main {
    public static void main(String[] args) {
        // Test the default constructor
        Hashmap hashmap = new Hashmap();
        System.out.println("Size After: " + hashmap.size());
        System.out.println("Empty hashmap created with default constructor:");
        // hashmap.Hashmap("2[-]");
        System.out.println(hashmap.toString());
        System.out.println();

        // Test the insert method
        hashmap.insert(78, 85);
        System.out.println(hashmap.toString());
        System.out.println("Size After: " + hashmap.size());

        hashmap.insert(21, 90);
        System.out.println(hashmap.toString());
        // System.out.println();
        System.out.println("Size After: " + hashmap.size());

        hashmap.insert(34, 58);
        System.out.println(hashmap.toString());
        // System.out.println();
        System.out.println("Size After: " + hashmap.size());

        hashmap.insert(34, 65);
        System.out.println(hashmap.toString());
        // System.out.println();
        System.out.println("Size After: " + hashmap.size());

        hashmap.insert(33, 45);
        System.out.println(hashmap.toString());
        // System.out.println();
        System.out.println("Size After Here: " + hashmap.size());

        hashmap.insert(63, 45);
        System.out.println(hashmap.toString());
        // System.out.println();
        System.out.println("Size After: " + hashmap.size());

        hashmap.insert(73, 45);
        System.out.println(hashmap.toString());
        // System.out.println();
        System.out.println("Size After: " + hashmap.size());

        hashmap.insert(93, 45);
        System.out.println(hashmap.toString());
        // System.out.println();
        System.out.println("Size After: " + hashmap.size());

        hashmap.insert(53, 45);
        System.out.println(hashmap.toString());
        // System.out.println();
        System.out.println("Size After: " + hashmap.size());

        hashmap.remove(73);
        System.out.println(hashmap.toString());
        // System.out.println();
        System.out.println("Size After: " + hashmap.size());

        hashmap.remove(63);
        System.out.println(hashmap.toString());
        // System.out.println();
        System.out.println("Size After: " + hashmap.size());

        System.out.println("\nHashmap after inserting two key-value pairs:");
        System.out.println(hashmap.toString());

        Test the search method
        KeyValuePair result = hashmap.search(33333334);
        System.out.println("\nSearch result for student number 33333334:");
        System.out.println(result != null ? result.toString() : "Not found");

        Test the remove method
        hashmap.remove(12345678);
        System.out.println("\nHashmap after removing student number 12345678 and insertion of 2222222:");
        hashmap.insert(22222222, 45);
        hashmap.insert(33333333, 56);
        hashmap.insert(33333334, 57);
        hashmap.insert(33333334, 58);
        System.out.println(hashmap.toString());

        // Test the toStringOneLine method
        String serialized = hashmap.toStringOneLine();
        System.out.println("\nSerialized hashmap:");
        System.out.println(serialized);

        Test the constructor that takes a string input
        Hashmap reconstructedHashmap = new Hashmap(serialized);
        System.out.println("\nReconstructed hashmap from serialized string:");
        System.out.println(reconstructedHashmap.toString());

        // Test the hash method
        int hashValue = hashmap.hash(12345678);
        System.out.println("\nHash value for student number 12345678:");
        System.out.println(hashValue); 
        System.out.println();

        PrimeNumberGenerator generator = new PrimeNumberGenerator();

        // Print the initial prime number
        System.out.println("Current prime: " + generator.currentPrime()); // Expected: 2

        Print the next few prime numbers
        System.out.println("Next prime: " + generator.nextPrime()); // Expected: 3
        System.out.println("Next prime: " + generator.nextPrime()); // Expected: 5
        System.out.println("Next prime: " + generator.nextPrime()); // Expected: 7
        System.out.println("Next prime: " + generator.nextPrime()); // Expected: 11

        // Print the entire sequence of prime numbers generated
        System.out.println("\nAll prime numbers generated:");
        System.out.println(generator.toString());

        System.out.println();
        System.out.println(hashmap.hash(2222222));
    }
}


// int hashIndex = hash(studentNumber);
        // int ogIndex = hashIndex;
    
        // int currentIndex = ogIndex;
    
        // while (true) {
        //     KeyValuePair pair = array[currentIndex];
        //     if (pair != null && pair.studentNumber == studentNumber) return pair;
        //     currentIndex = (currentIndex + 1) % array.length;
        //     if (currentIndex == ogIndex) break;
        // }
        // return null;
// ----------------Search that is Linear---------------
/*
for (int k = 0; k < array.length; k++) {
    if (array[k] != null && array[k].studentNumber == studentNumber) {
        array[k] = null;
        return;
    }
}

 */
