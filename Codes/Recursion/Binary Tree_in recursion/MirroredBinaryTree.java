public class MirroredBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {
    @Override
    public boolean contains(T data) {
        // TODO: Implement this function
        return contains(root, data);
    }

    private boolean contains(Leaf<T> leaf, T data) {
        if (leaf == null) {
            // System.out.println("Leaf not found.");
            return false;
        }
        System.out.println(leaf.toString());
        if (leaf.data.equals(data)) {
            return true;
        }
        if (data.compareTo(leaf.data) > 0) {
            return contains(leaf.left, data);
        } else {
            return contains(leaf.right, data);
        }
    }

    @Override
    public void depthFirstTraversal() {
        // TODO: Implement this function
        depthFirstTraversal(root);
    }

    private void depthFirstTraversal(Leaf<T> leaf) {
        if (leaf != null) {
            depthFirstTraversal(leaf.right);
            System.out.println(leaf.toString());
            // if (leaf.left == null && leaf.right == null) {
                // }
            depthFirstTraversal(leaf.left);
        }
    }

    @Override
    public int numLeavesInTree() {
        // TODO: Implement this function
        return numLeavesInTree(root);
    }

    private int numLeavesInTree(Leaf<T> leaf) {
        if (leaf == null) {
            return 0;
        }
        if (leaf.left == null && leaf.right == null) {
            return 1;
        }
        int f = numLeavesInTree(leaf.left) + numLeavesInTree(leaf.right);
        return f + 1;
    }

    @Override
    public int height() {
        // TODO: Implement this function
        return height(root) - 1;
    }

    private int height(Leaf<T> leaf) {
        if (leaf == null) {
            return 0;
        }
        int leftH = height(leaf.left);
        int rightH = height(leaf.right);
        return 1 + Math.max(leftH, rightH);
    }

    @Override
    public Leaf<T> findParent(T data) {
        // TODO: Implement this function
        if (root == null) {
            return null;
        }
        if (root.data.equals(data)) {
            System.out.println(root.toString());
            return null;
        }
        return fParent(root, data);
    }

    public Leaf<T> fParent(Leaf<T> rt, T data){
        //TODO: Implement this function
        if(rt == null){
            return null;
        }
        else if(rt.data.compareTo(data) == 0){
            System.out.println(rt.toString());
            return null;
        }
        else{
            System.out.println(rt.toString());

            if(data.compareTo(rt.data) > 0){
                boolean t = rt.left != null && rt.left.data.compareTo(data) == 0;
                int k = 4;
                if(t == true){
                    k = 1;
                }
                else{
                    k = 2;
                }
                switch(k){
                    case 1:
                        return rt;
                        // break;
                    case 2:
                        return fParent(rt.left, data);
                        // break;
                    default:
                        break;
                }
            }
            else{
                boolean t = rt.right != null && rt.right.data.compareTo(data) == 0;
                int k = 4;
                if(t == true){
                    k = 1;
                }
                else{
                    k = 2;
                }
                switch(k){
                    case 1:
                        return rt;
                        // break;
                    case 2:
                        return fParent(rt.right, data);
                        // break;
                    default:
                        break;
                }
            }
            return null;
        }
    }

    @Override
    public void insert(T data) {
        super.insert(data, false);
    }

    @Override
    public Leaf<T> find(T data) {
        // TODO: Implement this function
        return find(root, data);
    }

    private Leaf<T> find(Leaf<T> leaf, T data) {
        if (leaf == null) {
            // System.out.println("Leaf not found.");
            return null;
        }
        System.out.println(leaf.toString());
        if (leaf.data.equals(data)) {
            return leaf;
        }
        if (data.compareTo(leaf.data) > 0) {
            return find(leaf.left, data);
        } else {
            return find(leaf.right, data);
        }
    }

    @Override
    public boolean perfectlyBalanced() {
        // TODO: Implement this function
        if (root == null) {
            return true;
        }
        return numLeavesInTree(root.left) == numLeavesInTree(root.right);
    }

    @Override
    public BinaryTree<T> convertTree() {
        //TODO: Implement this function
        Leaf<T> rt = root;
        BinaryTree<T> reT = new StandardBinaryTree<>();
        reT.root = mirroBin(clone(rt));
        return reT;
    }

    public Leaf<T> clone(Leaf<T> rt){
        boolean mr = rt == null;
        int k = 4;
        if(mr == true){
            k = 1;
        }
        else{
            k = 2;
        }
        switch(k){
            case 1:
                return null;
            case 2:
                Leaf<T> cln = new Leaf<T>(rt.data);
        
                cln.right = clone(rt.right);
                cln.left = clone(rt.left);
        
                return cln;
        }
        return null;
	}

    public Leaf<T> mirroBin(Leaf<T> rt){
        boolean mr = rt == null;
        int k = 4;
        if(mr == true){
            k = 1;
        }
        else{
            k = 2;
        }
        switch(k){
            case 1:
                return null;
            case 2:
                Leaf<T> lt = mirroBin(rt.left);
                Leaf<T> rs = mirroBin(rt.right);
                rt.left = rs;
                rt.right = lt;
                return rt;
        }
        return null;
    }
}
