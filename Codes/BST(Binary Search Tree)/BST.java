public class BST<T extends Comparable<T>> {

    public BinaryNode<T> root;

    public BST() {
        this.root = null;
    }

    public void delete(T data) {
        root = deleteNode(root, data);
    }

    public boolean contains(T data) {
        BinaryNode<T> node = this.root;
        return containsHelper(node, data);
    }

    public void insert(T data) {
        root = insertNode(root, data);
    }

    public int getHeight() {
        BinaryNode<T> node = this.root;
        return HeightHelper(node);
    }

    public String printSearchPath(T data) {
        return printSearchPathHelper(root, data);
    }

    public int getNumLeaves() {
        BinaryNode<T> node = this.root;
        return getNumLeavesHelper(node);
    }

    public BST<T> extractBiggestSuperficiallyBalancedSubTree() {
        BST<T> res = new BST<T>();
        res.root = extractBiggestSubTree(root);
        return res;
    }

    public BinaryNode<T> getNode(T data) {
        return getNodeHelper(root, data);
    }

    public boolean isSuperficiallyBalanced() {
        BinaryNode<T> node = this.root;
        return (isSuperficiallyBalancedHelper(node) % 2 == 0);
    }

    public BinaryNode<T> findMax() {
        return findMaxHelper(root);
    }

    public BinaryNode<T> findMin() {
        return findMinHelper(root);
    }

    ///////////////

    private StringBuilder toString(BinaryNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != null) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.data.toString()).append("\n");
        if (node.left != null) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return root == null ? "Empty tree" : toString(root, new StringBuilder(), true, new StringBuilder()).toString();
    }

    //  HELPER FUNCTIONS:

    private int HeightHelper(BinaryNode<T> node) {

        if(isEmpty() == true || node == null) return 0;
        else {
            int nodeLeft = HeightHelper(node.left);
            int nodeRight = HeightHelper(node.right);
            return (Math.max(nodeLeft, nodeRight))+1;
        }
    }

    private boolean isEmpty() {
        return this.root == null;
    }

    private BinaryNode<T> deleteNode(BinaryNode<T> root, T data) {
        if (isEmpty() == true) return root;
    
        int compareResult = data.compareTo(root.data);
    
        if (compareResult < 0) {
            root.left = deleteNode(root.left, data);
        } else if (compareResult > 0) {
            root.right = deleteNode(root.right, data);
        } else if(root.left != null && root.right != null){
            root.data = findMinHelper(root.right).data;
            root.right = deleteNode(root.right, root.data);
        } else {
            root = (root.left != null) ? root.left : root.right;
            // return root;
        }
        return root;
    }

    // private BinaryNode<T> deleteNode(BinaryNode<T> root, T data) {

    //     if (isEmpty() == true) return root;
    
    //     int compareResult = data.compareTo(root.data);
    
    //     if (compareResult < 0)  root.left = deleteNode(root.left, data);
    //     else if (compareResult > 0) root.right = deleteNode(root.right, data);
    //     else {
    //         if (root.left == null) return root.right;
    //         else if (root.right == null) return root.left;
    //         root.data = findMinHelper(root.right).data;
    //         root.right = deleteNode(root.right, root.data);
    //     }
    //     return root;
    // }

    private BinaryNode<T> findMinHelper(BinaryNode<T> node) {
        if(node.left == null) return node;
        return findMinHelper(node.left);
    }

    private BinaryNode<T> insertNode(BinaryNode<T> node, T data) {

        if (node == null) return new BinaryNode<T>(data);
    
        int compareResult = data.compareTo(node.data);
    
        if (compareResult < 0) node.left = insertNode(node.left, data);
        else if (compareResult > 0) node.right = insertNode(node.right, data);
        return node;
    }

    private boolean containsHelper(BinaryNode<T> node, T data) {

        if(isEmpty() == true || node == null) return false;

        int compareResult = data.compareTo(node.data);
        if(compareResult == 0) return true;
        if(compareResult > 0) return containsHelper(node.right, data);
        else return containsHelper(node.left, data);
    }

    private String printSearchPathHelper(BinaryNode<T> node, T data) {
        if (node == null) {
            return "Null";
        }
    
        T index = node.data;
        String path = index.toString();
    
        int compareResult = data.compareTo(node.data);
    
        if (compareResult == 0) {
            return path;
        } else if (compareResult < 0) {
            String leftPath = printSearchPathHelper(node.left, data);
            if (!leftPath.equals("Null")) {
                return path + " -> " + leftPath;
            }
        } else {
            String rightPath = printSearchPathHelper(node.right, data);
            if (!rightPath.equals("Null")) {
                return path + " -> " + rightPath;
            }
        }
        return path + " -> Null";
    }    
    
    private int getNumLeavesHelper(BinaryNode<T> node){
        if(isEmpty() == true || node == null) return 0;
        
        if(node.left == null && node.right == null) return 1;

        int leftLeaves = getNumLeavesHelper(node.left);
        int rightLeaves = getNumLeavesHelper(node.right);

        return leftLeaves + rightLeaves;
    }

    private BinaryNode<T> getNodeHelper(BinaryNode<T> node, T data) {
        if(isEmpty() || node == null) return null;

        int compareResult = data.compareTo(node.data);

        if(compareResult == 0) return node;
        else if(compareResult < 0) return getNodeHelper(node.left, data);
        else return getNodeHelper(node.right, data);
    }

    private int isSuperficiallyBalancedHelper(BinaryNode<T> node) {
        int i = 1, k = 0;

        if(isEmpty() == true || node == null) return 0;
        else {
            int nodeLeft = isSuperficiallyBalancedHelper(node.left);
            int nodeRight = isSuperficiallyBalancedHelper(node.right);

            if(nodeLeft == nodeRight) return i;
            return k;
        }
    }

    private BinaryNode<T> extractBiggestSubTree(BinaryNode<T> node) {

        if(node == null) return null;

        int leftHeight = HeightHelper(node.left);
        int rightHeight = HeightHelper(node.right);
        
        if(Math.abs(leftHeight - rightHeight) <= 1) {
            node.left = extractBiggestSubTree(node.left);
            node.right = extractBiggestSubTree(node.right);
            return node;
        }
        else 
        {
            BinaryNode<T> leftSubtree = extractBiggestSubTree(node.left);
            BinaryNode<T> rightSubtree = extractBiggestSubTree(node.right);

            int leftsize = getNumLeavesHelper(leftSubtree);
            int rightsize = getNumLeavesHelper(rightSubtree);

            if(leftsize >= rightsize) return leftSubtree;
            else return rightSubtree;
        }
    }

    private BinaryNode<T> findMaxHelper(BinaryNode<T> node) {
        if(node == null) return null;
        if(node.right == null) return node;
        return findMaxHelper(node.right);
    }
}

