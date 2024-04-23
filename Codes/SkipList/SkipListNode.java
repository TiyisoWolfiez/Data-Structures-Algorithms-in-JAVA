// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipListNode<T extends Comparable<T>> {
    public T key;
    public SkipListNode<T>[] next;

    public SkipListNode(T key, int levels) {
        this.key = key;
        this.next = new SkipListNode[levels];
        for(int k = 0; k < levels ; k++){
            next[k] = null;
        } 
    }

    @Override
    public String toString() {
        return "["+ this.key.toString() + "]";
    }

    public String emptyString() {
        int k = toString().length();
        String p = "";
        for (int l = 0; l < k; l++) {
            p += "-";
        }
        return p;
    }
}
