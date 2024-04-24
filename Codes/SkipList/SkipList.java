 import java.util.Random;

// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<T>> {
    private int maxLevel;
    private SkipListNode<T>[] root;
    private int[] powers;
    // Do not change the seed. This is used to generate the same values every run
    private Random randomGenerator = new Random(123456);

    public SkipList(int maxLevel) {
        this.maxLevel = maxLevel;
        root = new SkipListNode[maxLevel];
        powers = new int[maxLevel];

        for(int k = 0; k < maxLevel; k++) {
            root[k] = null;
        }

        // Initialising the powers array
        powers[maxLevel - 1] = (2 << (maxLevel - 1)) - 1;
        for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++) {
            powers[i] = powers[i + 1] - (2 << j);
        }
    }

    public int chooseLevel() {
        if (powers[maxLevel - 1] <= 0) {
            throw new IllegalStateException("Invalid maxLevel configuration, resulting in division by zero.");
        }
    
        int i, r = Math.abs(randomGenerator.nextInt()) % powers[maxLevel - 1] + 1;
        for (i = 1; i < maxLevel; i++) {
            if (r < powers[i]) {
                return i - 1;
            }
        }
        return i - 1;
    }   
    
    public boolean isEmpty() {
        return root[0] == null;
    }

    public void insert(T key) {

        // Create two arrays to keep track of the current nodes and previous nodes
        SkipListNode<T>[] curr = new SkipListNode[maxLevel];
        SkipListNode<T>[] prev = new SkipListNode[maxLevel];
        SkipListNode<T> newNode;

        int lvl, k;
        curr[maxLevel-1] = root[maxLevel-1];
        prev[maxLevel - 1] = null;

        // Traverse the Skiplist from bottom to top
        for(lvl = maxLevel - 1; lvl >= 0; lvl--) {
            while(curr[lvl] != null && curr[lvl].key.compareTo(key) <= 0) {

                if(true){
                    prev[lvl] = curr[lvl];
                    curr[lvl] = curr[lvl].next[lvl];
                }
            }

            // If the key already exists in the Skiplist, return
            // if(curr[lvl] != null && curr[lvl].key.equals(key)){
            //     return;
            // }

            // Update the previous pointers
            if(lvl > 0) {
                if(prev[lvl] == null) {
                    curr[lvl - 1] = this.root[lvl - 1];
                    prev[lvl - 1] = null;
                } else {
                    curr[lvl - 1] = prev[lvl].next[lvl-1];
                    prev[lvl-1] = prev[lvl];
                }
            }
        }
        // Determine the level of the new node
        lvl = chooseLevel();
        newNode = new SkipListNode<T>(key, lvl + 1);

        // Update the next pointers
        for(k = 0; k <= lvl; k += 1) {
            newNode.next[k] = curr[k];
            if(prev[k] == null) {
                root[k] = newNode;
            } else {
                prev[k].next[k] = newNode;
            }
        }
    }

    public SkipListNode<T> search(T key) {
        int lvl = 0;
        for (lvl = this.maxLevel - 1; lvl >= 0 && this.root[lvl] == null; lvl--);
        SkipListNode<T> curr = this.root[lvl];
        SkipListNode<T> prev = this.root[lvl];
        
        while (true) {
            if (curr.key.compareTo(key) == 0) {
                return curr;
            } 
            else if (key.compareTo(curr.key) < 0) {

                if (lvl == 0) {
                    return null;
                } 
                else if (curr == (this.root[lvl])) {
                    curr = this.root[--lvl];
                } 
                else {
                    curr = prev.next[--lvl];
                }
            }
             else {

                prev = curr;

                if (curr.next[lvl] != null) {
                    curr = curr.next[lvl];
                }
                 else {
                    for (lvl -= 1; lvl >= 0 && curr.next[lvl] == null; lvl--)
                        ;
                    if (lvl >= 0) {
                        curr = curr.next[lvl];
                    } 
                    else {
                        return null;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String levels[] = new String[maxLevel];
    
    // Initialize the levels array
    for (int k = 0; k < levels.length; k++) {
        levels[k] = "[Lvl " + k + "]";
    }

    // Iterate over the levels and nodes
    for (int k = 0; k < 1; k++) {
        SkipListNode<T> ptr = root[k];

        while (ptr != null) {
            for (int i = 0; i < maxLevel && ptr != null; i++) {
                if (i < ptr.next.length) {
                    levels[i] += "->" + ptr.toString();
                } else {
                    levels[i] += "--" + ptr.emptyString();
                }
            }
            ptr = ptr.next[k];
        }
    }

    //Removing the excess Characters
    for(int k = 0; k < maxLevel; k++) {
        String string_1 = levels[k];
        for(int i = string_1.length() - 1; i >= 0; i--) {
            if(string_1.charAt(i) == ']'){
                levels[k] = string_1.substring(0, i+1);
                break;
            }
        }
    }

    //Concatenate
    String ps = "";
    for(int k = maxLevel - 1; k >= 0; k--) {
        ps += levels[k];
        if(k > 0) {
            ps += "\n";
        }
    }
    return ps;
}

public boolean delete(T key) {
    if (this.root == null || this.root[0] == null) {
        return false;
    }

    SkipListNode<T> node = search(key);

    // Check if the search result is null or if the key is not found
    if (node == null || !node.key.equals(key)) {
        return false;
    } else {
        int k = 0;
        for (; k < maxLevel; k += 1) {
            SkipListNode<T> curr = this.root[k];
            SkipListNode<T> prev = null;

            while (curr != null && curr != node) {
                prev = curr;
                curr = curr.next[k];
            }
            if (prev == null) {
                if (curr != null) {
                    this.root[k] = curr.next[k];
                } else {
                    this.root[k] = null;  // Corrected the assignment here
                }
            } else {
                if (curr != null) {
                    prev.next[k] = curr.next[k];
                } else {
                    prev.next[k] = null;  // Corrected the assignment here
                }
            }
        }
    }

    return true;
}



    public void printSearchPath(T key) {
        if(this.root == null || this.root[0] == null){
            return;
        }
        // Search for the key in the skip list
        SkipListNode<T> node = this.search(key);
    
        // If the key is not found, return
        if (node == null) {
            return;
        } else {
            // Initialize variables for traversal
            int lvl = 0;
            for (lvl = this.maxLevel - 1; lvl >= 0 && this.root[lvl] == null; lvl -= 1)
                ;
            SkipListNode<T> curr = this.root[lvl];
            SkipListNode<T> prev = this.root[lvl];
            String path = "";
    
            // Initialize the path with the current node's string representation
            path += curr.toString();
    
            // Traverse the skip list to find the key and print the path
            while (true) {
                if (curr.key.compareTo(key) == 0) {
                    // Print the path when the key is found
                    System.out.println(path);
                    return;
                } else if (key.compareTo(curr.key) < 0) {
                    // Adjust traversal for a smaller key
                    if (lvl == 0) {
                        return;
                    } else if (curr == (this.root[lvl])) {
                        curr = this.root[--lvl];
                        path += curr.toString();
                    } else {
                        curr = prev.next[--lvl];
                        path += curr.toString();
                    }
                } else {
                    // Adjust traversal for a larger key
                    prev = curr;
                    if (curr.next[lvl] != null) {
                        curr = curr.next[lvl];
                        path += curr.toString();
                    } else {
                        for (lvl--; lvl >= 0 && curr.next[lvl] == null; lvl -= 1)
                            ;
                        if (lvl >= 0) {
                            curr = curr.next[lvl];
                            path += curr.toString();
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    } 

}
