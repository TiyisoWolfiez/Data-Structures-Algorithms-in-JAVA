import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Define input and output file paths
            String inputFile = "a.txt";
            String outputFile = "b.txt";

            // Initialize Memory object and MultiwayMergeExternalSort object
            Memory memory = new Memory(500);
            MultiwayMergeExternalSort sorter = new MultiwayMergeExternalSort(memory, 3); // Example numFiles, adjust as needed

            // Open input and output files
            System.out.println("Opening files: ");
            sorter.open(inputFile, outputFile);

            // Perform initial sorting
            System.out.println("About to Sort:");
            sorter.initialSort();

            //Perform merging steps until all data is sorted
            System.out.println("Merging: ");
            while (!sorter.step()) {
                // Keep performing steps until all data is sorted
                sorter.initialSort();
            }

           // Write sorted data to output file
            sorter.writeToOutput();

           // Close input and output files
            sorter.close();

            System.out.println("Sorting completed successfully.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
