import java.io.*;

class MultiwayMergeExternalSort {
    private Memory memory;
    private int numFiles;

    private BufferedReader reader;
    private BufferedWriter writer;
    private BufferedWriter[] writers;

    public MultiwayMergeExternalSort(Memory memory, int numFiles)
            throws IOException {
        this.memory = memory;
        this.numFiles = numFiles;
        this.writers = new BufferedWriter[numFiles];
    }
    
    public void open(String inputFile, String outputFile) throws IOException {
        reader = new BufferedReader(new FileReader(inputFile));
        writer = new BufferedWriter(new FileWriter(outputFile));
    
        for (int i = 0; i < numFiles; i++) {
            writers[i] = new BufferedWriter(new FileWriter("tmp/T_a_" + (i) + ".tmp"));
        }
        for (int i = 0; i < numFiles; i++) {
            writers[i] = new BufferedWriter(new FileWriter("tmp/T_b_" + (i) + ".tmp"));
        }
    }    

    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
        if (writer != null) {
            writer.close();
        }
        for (BufferedWriter bw : writers) {
            if (bw != null) {
                bw.close();
            }
        }
    }

    public void initialSort() throws IOException {
        String tmpDirectory = "tmp";
        initialSort(tmpDirectory);
    }
    
    private void initialSort(String tmpDirectory) throws IOException {
        Integer[] chunk = new Integer[memory.getSize()];
        int read;
        while ((read = readChunk(chunk)) != -1) {
            quickSort(chunk, 0, read - 1);
            writeChunkToTempFiles(chunk, read, tmpDirectory);
        }
    }

    private int readChunk(Integer[] chunk) throws IOException {
        String line;
        int index = 0;
        while (index < chunk.length && (line = reader.readLine()) != null) {
            chunk[index++] = Integer.parseInt(line);
        }
        if (index == 0) {
            return -1;
        } else {
            return index;
        }
    }
    
    private void quickSort(Integer[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }
    
    private int partition(Integer[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
    

    private void writeChunkToTempFiles(Integer[] chunk, int size, String tmpDirectory) throws IOException {
        for (int i = 0; i < size; i++) {
            int fileIndex = i % numFiles;
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmpDirectory + "/T_b_" + (fileIndex ) + ".tmp", true));
            writer.write(String.valueOf(chunk[i]));
            writer.newLine();
            writer.close();
            System.out.println("Writing " + chunk[i] + " to temporary file " + tmpDirectory + "/T_b_" + (fileIndex) + ".tmp");
        }
    }

    public boolean step() throws IOException {
        class QueueItem {
            String line;
            BufferedReader reader;
            int fileIndex;

            QueueItem(String line, BufferedReader reader, int fileIndex) {
                this.line = line;
                this.reader = reader;
                this.fileIndex = fileIndex;
            }
        }

        class MyPriorityQueue {
            QueueItem[] array = new QueueItem[memory.getSize()];
            int size = 0;

            void add(QueueItem item) {
                array[size++] = item;
            }

            QueueItem poll() {
                if (isEmpty())
                    return null;
                QueueItem minItem = array[0];

                for (int i = 1; i < size; i++) {
                    array[i - 1] = array[i];
                }
                size--;
                return minItem;
            }

            boolean isEmpty() {
                return size == 0;
            }
        }
        MyPriorityQueue queue = new MyPriorityQueue();
        BufferedReader[] readers = new BufferedReader[numFiles];
      
        for (int i = 0; i < numFiles; i++) {
            readers[i] = new BufferedReader(new FileReader("tmp/T_b_" + i + ".tmp"));
            String line = readers[i].readLine();
            if (line != null) {
                queue.add(new QueueItem(line, readers[i], i));
            }
        }
        BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter("tmp/T_a_0.tmp"));

        while (!queue.isEmpty()) {
            QueueItem minItem = queue.poll();
            outputFileWriter.write(minItem.line + "\n");
            String nextLine = minItem.reader.readLine();
            if (nextLine != null) {
                queue.add(new QueueItem(nextLine, minItem.reader, minItem.fileIndex));
            }
        }
        for (BufferedReader reader : readers) {
            if (reader != null)
                reader.close();
        }
        outputFileWriter.close();
        return isFileSorted("tmp/T_a_0.tmp");
    }
    
    private boolean isFileSorted(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int previous = Integer.MIN_VALUE;
        while ((line = reader.readLine()) != null) {
            int current = Integer.parseInt(line);
            if (current < previous) {
                reader.close();
                return false;
            }
            previous = current;
        }
        reader.close();
        return true;
    }

    public void writeToOutput() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("tmp/T_a_0.tmp"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("b.txt"));
    
        String line;
        String previousLine = null;
        while ((line = reader.readLine()) != null) {
            if (previousLine != null) {
                writer.write(previousLine);
                writer.newLine();
            }
            previousLine = line;
        }
    
        if (previousLine != null) {
            writer.write(previousLine);
        }
    
        reader.close();
        writer.close();
    }                      
}
