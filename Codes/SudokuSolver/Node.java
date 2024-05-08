public class Node<T> {
    public T data;
    public Node<T> next;

    public String toString() {
        return data.toString();
    }

    public Node(T val) {
        next = null;
        data = val;
    }
}
