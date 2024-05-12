public class BTree<T extends Comparable<T>> {
    public BTreeNode<T> root;
    public int m;

    public BTree(int m) {
        if (m % 2 == 0) throw new IllegalArgumentException("Order must be odd.");
        this.m = m;
        this.root = null;
    }

    public void insert(T data) {
        if (root == null) root = new BTreeNode<>(m);
        int k = 0;
        insertRecursive(root, data, k);
    }

    public void insertRecursive(BTreeNode<T> node, T data, int k) {
        int childs = countNonNullChildren(node);
        if (childs == 0) {
            int keys = countNonNullKeys(node);
            keys++;

            int i;
            for (i = node.size - 1; i > 0 && (node.nodeData[i-1] == null || node.nodeData[i-1].compareTo(data) > 0); i--) node.nodeData[i] = node.nodeData[i-1];
            node.nodeData[i] = data;
            
            if (keys == node.size) splitNode(node, k);
        } else {
            int i;
            for (i = node.size - 1; i >= 0; i -= 1) {
                if (node.nodeData[i] != null && node.nodeData[i].compareTo(data) <= 0 || i == 0) {
                    if (node.nodeData[i].compareTo(data) <= 0) insertRecursive(node.nodeChildren[i+1], data, i+1);
                    else insertRecursive(node.nodeChildren[i], data, i);

                    int keys = 0;
                    for (int j = 0; j < node.size; j += 1) if (node.nodeData[j] != null) keys++;
                    if (keys == node.size) splitNode(node, k);
                    break;
                }
            }
        }
    }
    private int countNonNullChildren(BTreeNode<T> node) {
        int count = 0;
        for (int i = 0; i < node.size; i++) {
            if (node.nodeChildren[i] != null) {
                count++;
            }
        }
        return count;
    }

    private int countNonNullKeys(BTreeNode<T> node) {
        int count = 0;
        for (int i = 0; i < node.size; i++) {
            if (node.nodeData[i] != null) {
                count++;
            }
        }
        return count;
    }

    private void splitNode(BTreeNode<T> node, int k) {
        int i;
        int mid = (m - 1) / 2 + ((m - 1) % 2);
        BTreeNode<T> leftNode = new BTreeNode<>(m);
        BTreeNode<T> rightNode = new BTreeNode<>(m);
        
        for (i = 0; i < mid; i += 1) {
            leftNode.nodeData[i] = node.nodeData[i];
            leftNode.nodeChildren[i] = node.nodeChildren[i];
            if (node.nodeChildren[i] != null)  node.nodeChildren[i].parent = leftNode;
        }
        leftNode.nodeChildren[i] = node.nodeChildren[i];
        if (node.nodeChildren[i] != null) node.nodeChildren[i].parent = leftNode;

        int j = mid + 1;
        for (i = 0; j < node.size; j++, i++) {
            rightNode.nodeData[i] = node.nodeData[j];
            rightNode.nodeChildren[i] = node.nodeChildren[j];
            if (rightNode.nodeChildren[i] != null) rightNode.nodeChildren[i].parent = rightNode;
        }
        rightNode.nodeChildren[i] = node.nodeChildren[j];
        if (rightNode.nodeChildren[i] != null)  rightNode.nodeChildren[i].parent = rightNode;
        insertNodeIntoParent(node, k, leftNode,  rightNode,mid);
    }

    private void insertNodeIntoParent(BTreeNode<T> node, int k, BTreeNode<T> leftNode, BTreeNode<T> rightNode, int mid) {
        if (node.parent == null) {
            BTreeNode<T> pNode = new BTreeNode<>(m);
    
            pNode.nodeData[0] = node.nodeData[mid];
            pNode.nodeChildren[0] = leftNode;
            pNode.nodeChildren[1] = rightNode;
            rightNode.parent = pNode;
            leftNode.parent = pNode;
            root = pNode;
        } else {
            leftNode.parent = node.parent;
            rightNode.parent = node.parent;
            int i = node.parent.size;
            for (; i > k && i > 0; i--) {
                node.parent.nodeChildren[i] = node.parent.nodeChildren[i - 1];
            }
            node.parent.nodeChildren[k] = leftNode;
            node.parent.nodeChildren[k + 1] = rightNode;
    
            i = node.parent.size - 1;
            for (; i > 0 && (node.parent.nodeData[i - 1] == null || node.parent.nodeData[i - 1].compareTo((T) node.nodeData[mid]) > 0); i--) {
                if (node.parent.nodeData[i - 1] != null && node.parent.nodeData[i - 1].compareTo((T) node.nodeData[mid]) <= 0) {
                    break;
                }
                node.parent.nodeData[i] = node.parent.nodeData[i - 1];
            }
            node.parent.nodeData[i] = node.nodeData[mid];
        }
    }
    

    public String printPath(T key) {
        StringBuilder pathBuilder = new StringBuilder();
        return printPath(root, key, pathBuilder).toString();
    }
    
    public StringBuilder printPath(BTreeNode<T> node, T key, StringBuilder pathBuilder) {
        if (node != null) {
            for (int i = 0; i < node.size; i++) {
                if (node.nodeData[i] != null) {
                    if (node.nodeData[i].compareTo(key) <= 0) {
                        if (pathBuilder.length() > 0) {
                            pathBuilder.append(" -> ");
                        }
                        pathBuilder.append(node.nodeData[i]);
                        if (node.nodeData[i].equals(key)) {
                            return pathBuilder;
                        } else if (i + 1 == node.size) {
                            return printPath(node.nodeChildren[i + 1], key, pathBuilder);
                        }
                    }
    
                    if (node.nodeData[i].compareTo(key) > 0) {
                        if (i == 0) {
                            if (pathBuilder.length() > 0) {
                                pathBuilder.append(" -> ");
                            }
                            pathBuilder.append(node.nodeData[i]);
                            return printPath(node.nodeChildren[0], key, pathBuilder);
                        } else {
                            return printPath(node.nodeChildren[i], key, pathBuilder);
                        }
                    }
                } else {
                    return printPath(node.nodeChildren[i], key, pathBuilder);
                }
            }
        }
        if (pathBuilder.length() > 0) {
            pathBuilder.append(" -> ");
        }
        pathBuilder.append("Null");
        return pathBuilder;
    }             

    /* -------------------------------------------------------------------------- */
    /* Please don't change this toString, I tried to make it pretty. */
    /* -------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------- */
    /* Also we may test against it */
    /* -------------------------------------------------------------------------- */

    @Override
    public String toString() {
        if (root == null) {
            return "The B-Tree is empty";
        }
        StringBuilder builder = new StringBuilder();
        buildString(root, builder, "", true);
        return builder.toString();
    }

    private void buildString(BTreeNode<T> node, StringBuilder builder, String prefix, boolean isTail) {
        if (node == null)
            return;

        builder.append(prefix).append(isTail ? "└── " : "├── ");
        for (int i = 0; i < node.nodeData.length; i++) {
            if (node.nodeData[i] != null) {
                builder.append(node.nodeData[i]);
                if (i < node.nodeData.length - 1 && node.nodeData[i + 1] != null) {
                    builder.append(", ");
                }
            }

        }
        if (node.parent != null)
            builder.append("\t(p:" + node.parent.nodeData[0].toString() + ")");
        builder.append("\n");

        int numberOfChildren = m;
        for (int i = 0; i < numberOfChildren; i++) {

            BTreeNode<T> child = node.descend(i);
            buildString(child, builder, prefix + (isTail ? "    " : "│   "), i == numberOfChildren - 1);
        }
    }
}


/*
 └── 10
    ├── 3, 6    (p:10)
    │   ├── 1   (p:3)
    │   ├── 5   (p:3)
    │   └── 7, 9        (p:3)
    ├── 20, 25  (p:10)
    │   ├── 17  (p:20)
    │   ├── 20  (p:20)
    │   └── 30  (p:20)
 */
