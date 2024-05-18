public class LinkedList {
    public CoordinateNode head;

    public LinkedList() {
        this.head = null;
    }

    public LinkedList(int x, int y) {
        CoordinateNode newNode = new CoordinateNode(x, y);
        newNode.next = head;
        head = newNode;
    }

    public void append(int x, int y) {
        head = appendRecursive(head, x, y);
    }

    public void appendList(LinkedList other) {

        if(other == null || other.head == null) return;
        head = appendListRecursive(head, other.head);
    }

    public boolean contains(int x, int y) {
        return containsRecursive(head, x, y);
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "Empty List";
        }
        else {
            return head.toString() + toStringRecursive(head.next);
        }
    }

    public int length() {
        if(isEmpty()) return 0;
        else{
            return lengthRecursive(head);
        }
    }

    public LinkedList reversed() {
        LinkedList reversedList = new LinkedList();
        reversedList.head = reversedRecursive(head);
        return reversedList;
    }

    private CoordinateNode appendRecursive(CoordinateNode node, int x, int y) {

        if(node == null) return new CoordinateNode(x, y);
        node.next = appendRecursive(node.next, x, y);
        return node;
    }

    private CoordinateNode appendListRecursive(CoordinateNode curr, CoordinateNode otherNode) {

        if(curr == null) return otherNode;
        curr.next = appendListRecursive(curr.next, otherNode);
        return curr;
    }

    private boolean containsRecursive(CoordinateNode node, int x, int y) {
        if(node == null) return false;
        else if(node.x == x && node.y == y) return true;
        else {
            return containsRecursive(node.next, x, y);
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    private String toStringRecursive(CoordinateNode node) {
        if(node == null) return "";
        else {
            return " -> " + node.toString() + toStringRecursive(node.next);
        }
    }

    private int lengthRecursive(CoordinateNode node) {
        if(node == null) return 0;
        return 1 + lengthRecursive(node.next);
    }

    private CoordinateNode reversedRecursive(CoordinateNode node) {
        if(node == null || justHelper(node)) return node;
        CoordinateNode reversedList = reversedRecursive(node.next);
        node.next.next = node;
        node.next = null;
        return reversedList;
    }
    
    private boolean justHelper(CoordinateNode node) {
        if(node == null) return false;
        return node.next == null;
    }

    public boolean contains(CoordinateNode node) {
        return containsRecursive(head, node);
    }
    
    private boolean containsRecursive(CoordinateNode current, CoordinateNode node) {
        if (current == null) return false;
    
        if (current.equals(node)) return true;
        return containsRecursive(current.next, node);
    }
    

    //---------------------------------------------------------
    public CoordinateNode getLast(CoordinateNode current) {
        if (current == null) {
            return null;
        }
        if (current.next == null) {
            return current;
        }
        return getLast(current.next);
    }

    public CoordinateNode getLast() {
        return getLast(head);
    }

    public CoordinateNode getFirst() {
        return head;
    }
}
