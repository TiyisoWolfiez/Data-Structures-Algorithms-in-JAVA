public abstract class Heap<T extends Comparable<T>> {

    public Comparable<T>[] data;
    public int size;

    public Heap() {
        this.data = (Comparable<T>[]) new Comparable[2];
        size = 0;
    }

    public Heap(T[] array) {
        this();
        this.data =(Comparable<T>[]) new Comparable[array.length];
        this.size = array.length;

        int k = 0;
        for(; k < array.length; k+=1){
            this.data[k] = array[k];
        }

        int lastNonLeaf = (size -1)/2;
        int i = lastNonLeaf;
        for(;i >=0;i--){
            percolateDown(i);
        }
    }

    private void percolateDown(int elem) {
        int k = (size - 2) / 2;
        while (elem <= k) {
            int leftChild = getLeftChildIndex(elem);
            int rightChild = getRightChildIndex(elem);
            int biggerChild = leftChild;
    
            if (rightChild < size && compare(data[rightChild], data[leftChild])) {
                biggerChild = rightChild;
            }
            if (biggerChild < size && compare(data[biggerChild], data[elem])) {
                Comparable<T> temp = data[elem];
                data[elem] = data[biggerChild];
                data[biggerChild] = temp; 
                elem = biggerChild;
            } else {
                break;
            }
        }
    }

    protected abstract boolean compare(Comparable<T> child, Comparable<T> parent);

    @SuppressWarnings("unchecked")
    public void push(T data) {
        if(data == null) 
            throw new IllegalArgumentException("Empty heap.");

        if (isFull()) {
            enlargeArray(this.data.length * 2);
        }
    
        int hole = size;
        Comparable<T> item = data;

        while (hole > 0 && compare(data, (T)this.data[(hole - 1) / 2])) {
            this.data[hole] = this.data[(hole - 1) / 2];
            hole = (hole - 1) / 2;
        }
    
        this.data[hole] = item;
        size++;
    }

    private boolean isFull() {
        return size == data.length;
    }

    @SuppressWarnings("unchecked")
    private void enlargeArray(int newSize) {
        Comparable<T>[] old = this.data;
        this.data = (Comparable<T>[]) new Comparable[newSize];
        int i = 0;
        for (; i < old.length; i++) {
            this.data[i] = old[i];
        }
    }

    public Comparable<T> pop() {
        if (isEmpty()) {
            return null;
        }
        Comparable<T> root = data[0];
    
        int k = size - 1;
        data[0] = data[k];
        size--;

        percolateDown(0);
        return root;
    }

    public Comparable<T> peek() {
        return data[0];
    }

    private boolean isEmpty() {
        return size == 0;
    }
    /*
     * 
     * Functions used for the toString
     * Do not change them but feel free to use them
     * 
     */

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(0, "", true, sb); // Start from the root
        return sb.toString();
    }

    private void buildString(int index, String prefix, boolean isTail, StringBuilder sb) {
        if (index < size) {
            String linePrefix = isTail ? "└── " : "┌── ";
            if (getRightChildIndex(index) < size) { // Check if there is a right child
                buildString(getRightChildIndex(index), prefix + (isTail ? "|   " : "    "), false, sb);
            }
            sb.append(prefix).append(linePrefix).append(data[index]).append("\n");
            if (getLeftChildIndex(index) < size) { // Check if there is a left child
                buildString(getLeftChildIndex(index), prefix + (isTail ? "    " : "│   "), true, sb);
            }
        }
    }

}
