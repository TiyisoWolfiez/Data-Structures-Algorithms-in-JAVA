class Memory {
    private Integer[] data;
    private int size;

    public Memory(int size) {
        this.data = new Integer[size];
        this.size = size;
    }

    public Integer get(int index) {
        return data[index];
    }

    public void set(int index, int value) {
        data[index] = value;
    }

    public int getSize() {
        return size;
    }

    public Integer[] getData() {
        return data;
    }

    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
    }
}
