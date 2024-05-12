public class BTreeNode<T extends Comparable<T>> {
    public Comparable<T>[] nodeData;
    public BTreeNode<T>[] nodeChildren;
    public BTreeNode<T> parent;
    public int size;

    @SuppressWarnings("unchecked")
    public BTreeNode(int size) {
        this.size = size;
        this.nodeData = new Comparable[2*this.size - 1];
        this.nodeChildren = new BTreeNode[2*this.size];
        this.parent = null;
    }

    public Comparable<T> getIndex(int i) {
        if(i >= 0 && i < nodeData.length) {
            return nodeData[i];
        } else {
            return null;
        }
    }

    public BTreeNode<T> ascend() {
        return parent;
    }

    public BTreeNode<T> descend(int i) {
        if(i >= 0 && i < nodeChildren.length) {
            return nodeChildren[i];
        } else {
            return null;
        }
    }
    
    /* -------------------------------------------------------------------------- */
    /* Helpers */
    /* -------------------------------------------------------------------------- */

    public String toString() {
        String out = "|";
        for (int i = 0; i < nodeData.length; i++) {
            if (nodeData[i] == null) {
                out += "null|";
            } else {
                out += nodeData[i].toString() + "|";
            }

        }
        return out;
    }
}
