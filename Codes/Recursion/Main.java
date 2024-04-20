public class Main {
    public static void main(String[] args) {
        RecursiveArray recursiveArray = new RecursiveArray("1,2,3,4,5");
        
        System.out.println("Array length: " + recursiveArray.array.length);
        System.out.println("Array contents:");
        for (Integer num : recursiveArray.array) {
            System.out.print(num+" ");
        }
        System.out.println("\n");

        // Expected Output:
        /*Array length: 5
        Array contents:
        1 2 3 4 5 */

        RecursiveArray emptyArray = new RecursiveArray("");
        System.out.println(emptyArray.toString());  // Output: Empty Array

        RecursiveArray array = new RecursiveArray("1,2,3,4,5");
        System.out.println(array.toString()); 

        // Expected Output:
        /*  
         * Empty Array
           [1,2,3,4,5]
         */
        System.out.println();

         RecursiveArray array1 = new RecursiveArray("1,2,3,4,5");
        System.out.println("Initial Array: " + array1.toString());  // Output: [1, 2, 3, 4, 5]

        array1.append(6);
        array1.append(8);
        array1.append(10);
        System.out.println("Array after appending: " + array1.toString()); 

        array1.append(7);
        System.out.println("Array after appending 7: " + array1.toString());  // Output: [1, 2, 3, 4, 5, 6, 7]

        /*Expected Output:
         *  Initial Array: [1,2,3,4,5]
            Array after appending: [1,2,3,4,5,6,8,10]
            Array after appending 7: [1,2,3,4,5,6,8,10,7]
         */

         System.out.println();

         RecursiveArray array2 = new RecursiveArray("1,2,3,4,5");
        System.out.println("Initial Array: " + array2.toString());  // Output: [1, 2, 3, 4, 5]

        array2.prepend(0);
        System.out.println("Array after prepending 0: " + array2.toString());  // Output: [0, 1, 2, 3, 4, 5]

        array2.prepend(-1);
        System.out.println("Array after prepending -1: " + array2.toString());  // Output: [-1, 0, 1, 2, 3, 4, 5]

        /* 
         * Expected Output:
         * Initial Array: [1,2,3,4,5]
            Array after prepending 0: [0,1,2,3,4,5]
            Array after prepending -1: [-1,0,1,2,3,4,5]
        */

        System.out.println("\n");
        RecursiveArray array3 = new RecursiveArray("1,2,3,4,5");
        System.out.println("Initial Array: " + array3.toString());  // Output: [1, 2, 3, 4, 5]

        System.out.println("Contains 3: " + array3.contains(3));  // Output: true
        System.out.println("Contains 6: " + array3.contains(6)); 
        /* Expected Output: 
         * Initial Array: [1,2,3,4,5]
            Contains 3: true
            Contains 6: false
        */

        System.out.println("\n");
        RecursiveArray array4 = new RecursiveArray("1,2,3,4,5");
        System.out.println("Initial Array: " + array4.toString());  // Output: [1, 2, 3, 4, 5]

        System.out.println("Is ascending? " + array4.isAscending());
        System.out.println("Is descending? " + array4.isDescending());  // Output: true

        RecursiveArray array5 = new RecursiveArray("5,4,3,2,1");
        System.out.println("Initial Array: " + array5.toString());  // Output: [5, 4, 3, 2, 1]

        System.out.println("Is descending? " + array5.isDescending());
        System.out.println("Is ascending? " + array5.isAscending());

        RecursiveArray array6 = new RecursiveArray("1,1,1,1");
        System.out.println("Initial Array: " + array6.toString());  // Output: [1, 1, 1, 1]

        System.out.println("Is ascending? " + array6.isAscending());  // Output: true
        System.out.println("Is descending? " + array6.isDescending());  // Output: true

        /* Expected Output:
         * Initial Array: [1,2,3,4,5]
            Is ascending? true
            Initial Array: [5,4,3,2,1]
            Is ascending? false
         */
        System.out.println("\n");
        RecursiveArray array7 = new RecursiveArray("5,4,3,2,1");
        System.out.println("Initial Array: " + array7.toString());  // Output: [5, 4, 3, 2, 1]

        array7.sortAscending();
        System.out.println("Sorted Array (Ascending): " + array7.toString());  // Output: [1, 2, 3, 4, 5]

        RecursiveArray array8 = new RecursiveArray("9,3,7,1,5");
        System.out.println("Initial Array: " + array8.toString());  // Output: [9, 3, 7, 1, 5]

        array8.sortAscending();
        System.out.println("Sorted Array (Ascending): " + array8.toString());

        System.out.println("\n");

        RecursiveArray array9 = new RecursiveArray("5,4,3,2,1,7,9,10,6");
        System.out.println("Initial Array: " + array9.toString());  // Output: [5, 4, 3, 2, 1]

        array9.sortDescending();
        System.out.println("Sorted Array (Descending): " + array9.toString());

    }
}
