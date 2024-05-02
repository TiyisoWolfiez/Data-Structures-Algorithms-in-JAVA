public class MaxSkewHeap {
    Node root;

    @Override
    public String toString() {
        return (root == null ? "Empty Tree" : toString(root, "", true));
    }

    public String toString(Node node, String prefix, boolean end) {
        String res = "";
        if (node.right != null) {
            res += toString(node.right, prefix + (end ? "│   " : "    "), false);
        }
        res += prefix + (end ? "└── " : "┌── ") + node.toString() + "\n";
        if (node.left != null) {
            res += toString(node.left, prefix + (end ? "    " : "│   "), true);
        }
        return res;
    }

    public String toStringOneLine() {
        return (root == null ? "{}" : toStringOneLine(root));
    }

    public String toStringOneLine(Node ptr) {
        if (ptr == null) {
            return "{}";
        }
        return "{" + ptr.toString() + toStringOneLine(ptr.left) + toStringOneLine(ptr.right) + "}";
    }

    public MaxSkewHeap() {
        this.root = null;
    }

    public MaxSkewHeap(String input) {
        root = constructHeap(input);
    }

    private int findMatchingBrace(String str, int start) {
        int openBraces = 0;
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) == '{') openBraces++;
            else if (str.charAt(i) == '}') {
                openBraces--;
                if (openBraces == 0) return i;
            }
        }
        return -1;
    }

    public Node constructHeap(String input) {
        if (input.equals("{}")) {
            return null;
        }

        input = input.substring(1, input.length() - 1);
        int i = 0;
        while (i < input.length() && input.charAt(i) != '{' && input.charAt(i) != '}') {
            i++;
        }

        int data = Integer.parseInt(input.substring(0, i));
        int matchingBrace = findMatchingBrace(input, i);
        String leftDescent = input.substring(i, matchingBrace + 1);
        String rightDescent = input.substring(matchingBrace + 1);

        Node leftNode = constructHeap(leftDescent);
        Node rightNode = constructHeap(rightDescent);

        Node currNode = new Node(data);
        currNode.left = leftNode;
        currNode.right = rightNode;

        return currNode;
    }

    public void insert(int data) {

        if (contains(root, data)) {
            return;
        }
        Node newNode = new Node(data);
        root = merge(root, newNode);
    }

    private Node merge(Node j, Node k) {
        if(j == null) return k;
        if(k == null) return j;

        if(j.data < k.data) {
            Node temp = j;
            j = k;
            k = temp;
        }

        j.right = merge(j.right, k);

        swapChildren(j);

        return j;
    }

    private void swapChildren(Node node) {
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    private boolean contains(Node node, int data) {
        if (node == null) return false;
        if (node.data == data) return true;
        return contains(node.left, data) || contains(node.right, data);
    }

    public void remove(int data) {
        if(root == null) return;
        root = remove(root, data);
    }

    private Node remove(Node node, int data) {
        if(node == null) return null;
        if(node.data == data) return merge(node.left, node.right);
        else {
            node.left = remove(node.left, data);
            node.right = remove(node.right, data);
            return node;
        }
    }

    public Node search(int value) {
        return search(root, value);
    }

    private Node search(Node node, int value) {
        if(node == null || compare(node.data, value)) return null;
        else if(node.data == value) return node;
        else {
            Node result = search(node.right, value);
            if(result != null) return result;

            return search(node.left, value);
        }
    }

    private boolean compare(int k, int j) {
        return k < j;
    }    

    public String searchPath(int value) {
        if (root == null || compare(root.data,value)) {
            if (root == null) return "";
            else return String.valueOf(root.data);       
        }

        StringBuilder path = new StringBuilder();
        searchPath(root, value, path);
        return path.toString();
    }

    private boolean searchPath(Node node, int value, StringBuilder path) {
        if (node == null) return false;

        if (path.length() > 0) path.append("->");

        if (node.data == value) {
            path.append("[").append(node.data).append("]");
            return true;
        } else path.append(node.data);

        boolean foundInRight = searchPath(node.right, value, path);
        if (foundInRight) return true;

        return searchPath(node.left, value, path);
    }

    public boolean isLeftist() {
        return isLeftist(root);
    }

    private boolean isLeftist(Node node) {
        if (node == null) return true;

        int leftLength = pathLength(node.left);
        int rightLength = pathLength(node.right);


        return leftLength >= rightLength && isLeftist(node.left) && isLeftist(node.right);
    }

    public boolean isRightist() {
        return isRightist(root);
    }

    private boolean isRightist(Node node) {
        if (node == null) return true;

        int leftLength = pathLength(node.left);
        int rightLength = pathLength(node.right);

        return rightLength >= leftLength && isRightist(node.left) && isRightist(node.right);
    }

    private int pathLength(Node node) {
        if (node == null) return 0;

        int leftLength = pathLength(node.left);
        int rightLength = pathLength(node.right);

        return 1 + Math.min(leftLength, rightLength);
    }
}
