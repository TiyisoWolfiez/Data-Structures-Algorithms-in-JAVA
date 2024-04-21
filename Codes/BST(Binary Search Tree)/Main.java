public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        // Insert nodes
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);
        bst.insert(100);

        System.out.println("Binary Search Tree:");
        System.out.println(bst);
        System.out.println("\n");

        // Test delete
        System.out.println("\nDeleting 20:");
        bst.delete(20);
        System.out.println(bst);
        System.out.println("\n");

        System.out.println("\nDeleting 30:");
        bst.delete(30);
        System.out.println(bst);
        System.out.println("\n");

        System.out.println("\nDeleting 50 (root):");
        bst.delete(50);
        System.out.println(bst);
        System.out.println("\n");

        // Test contains
        System.out.println("\nSearching for 60:");
        System.out.println("Contains 60? " + bst.contains(60));
        System.out.println("\n");

        System.out.println("\nSearching for 20:");
        System.out.println("Contains 20? " + bst.contains(20));
        System.out.println("\n");

        // Test getHeight
        System.out.println("\nHeight of the tree: " + bst.getHeight());
        System.out.println("\n");

        // Test isSuperficiallyBalanced
        System.out.println("\nIs the tree superficially balanced? " + bst.isSuperficiallyBalanced());

        // Test findMax and findMin
        System.out.println("\nMax element in the tree: " + bst.findMax().data);
        System.out.println("Min element in the tree: " + bst.findMin().data);

        // Test getNode
        System.out.println("\nGetting node with data 70: " + bst.getNode(70));
        System.out.println("Getting node with data 100: " + bst.getNode(100));

        // Test printSearchPath
        System.out.println("\nPrint search path for 80:");
        System.out.println(bst.printSearchPath(80));

        System.out.println("\nPrint search path for 110:");
        System.out.println(bst.printSearchPath(110));

        // Test extractBiggestSuperficiallyBalancedSubTree
        BST<Integer> balancedSubTree = bst.extractBiggestSuperficiallyBalancedSubTree();
        System.out.println("\nExtracted biggest superficially balanced subtree:");
        System.out.println(balancedSubTree);

        // Test getNumLeaves
        System.out.println("\nNumber of leaves in the tree: " + bst.getNumLeaves());

        // Additional tests
        System.out.println("\nAdding more elements for additional tests:");
        bst.insert(10);
        bst.insert(5);
        bst.insert(25);
        bst.insert(35);
        bst.insert(55);
        bst.insert(65);
        bst.insert(75);
        bst.insert(95);

        System.out.println("\nBinary Search Tree after additional insertions:");
        System.out.println(bst);
        System.out.println("\nHeight of the tree after additional insertions: " + bst.getHeight());

        System.out.println("\nIs the tree superficially balanced after additional insertions? " + bst.isSuperficiallyBalanced());
        System.out.println("\nNumber of leaves in the tree after additional insertions: " + bst.getNumLeaves());

        System.out.println("\nExtracted biggest superficially balanced subtree after additional insertions:");
        BST<Integer> balancedSubTree2 = bst.extractBiggestSuperficiallyBalancedSubTree();
        System.out.println(balancedSubTree2);

        System.out.println("\n");

        BST<Integer> bst1 = new BST<>();

        // Insert nodes
        bst1.insert(10);
        bst1.insert(15);
        bst1.insert(50);
        bst1.insert(75);
        bst1.insert(60);
        bst1.insert(100);
        bst1.insert(150);
        bst1.insert(125);
        bst1.insert(140);
        bst1.insert(175);
        bst1.insert(190);
        bst1.insert(165);
        bst1.insert(110);

        bst1.insert(55);
        bst1.insert(65);
        bst1.insert(52);
        bst1.insert(56);
        bst1.insert(63);
        bst1.insert(70);

        System.out.println("Binary Search Tree:");
        System.out.println(bst1);
        System.out.println("\n");

        // Extract the biggest superficially balanced subtree
        BST<Integer> balancedSubTree1 = bst1.extractBiggestSuperficiallyBalancedSubTree();
        System.out.println("Extracted biggest superficially balanced subtree:");
        System.out.println(balancedSubTree1);
    }
}
