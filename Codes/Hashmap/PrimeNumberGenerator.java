public class PrimeNumberGenerator {
    PrimeNode head;

    @Override
    public String toString() {
        String res = head.toString();
        PrimeNode ptr = head.next;
        while (ptr != null) {
            res += "->" + ptr.toString();
            ptr = ptr.next;
        }
        return res;
    }

    public PrimeNumberGenerator() {
        this.head = new PrimeNode(2);
    }

    public int currentPrime() {
        return head.value;
    }

    public int nextPrime() {
        if(head.next == null) sieveOfEratosthenes();
        head = head.next;
        return head.value;
    }

    public void sieveOfEratosthenes() {
        boolean[] notPrime = new boolean[head.value * 2 + 1];

        int jump = 2;

        while(jump < notPrime.length){
            int counter = jump + jump;

            while(counter < notPrime.length){
                notPrime[counter] = true;
                counter += jump;
            }
            jump += 1;
        }
        PrimeNode current = head;
        for(int k = head.value + 1; k < notPrime.length; k += 1){
            if(!notPrime[k]){
                current.next = new PrimeNode(k);
                current = current.next;
            }
        }
    }
}
