
public class Main {
    public static void main(String[] args) throws Exception {
        Integer[] array = {4, 10, 3, 5, 1};
        Heap<Integer> heap = new MaxHeap<>(array);

        System.out.println("Initial Heap:");
        System.out.println(heap);

        // Test push operation
        heap.push(15);
        heap.push(20);
        heap.push(12);
        heap.push(3);
        System.out.println("\nHeap after pushing:");
        System.out.println(heap);
        System.out.println();

        System.out.println("Pop Testing:");
        heap.pop();
        System.out.println(heap);
        System.out.println();
        heap.pop();
        System.out.println(heap);
        System.out.println();

        System.out.println("-----------------MinHeap Testing------------------");
        Integer[] arrayy = {4, 10, 3, 5, 1};
        Heap<Integer> heapy = new MinHeap<>(arrayy);

        // Test push operation
        heapy.push(15);
        heapy.push(20);
        heapy.push(12);
        heapy.push(3);
        System.out.println("\nHeapy after pushing:");
        System.out.println(heapy);
        System.out.println();

        System.out.println("Pop Testing:");
        heapy.pop();
        System.out.println(heapy);
        System.out.println();
        heapy.pop();
        System.out.println(heapy);
        System.out.println();

        System.out.println(heap.peek());
        System.out.println(heapy.peek());

        Integer[] arrayyy = {};
        Heap<Integer> heapyy = new MinHeap<>(arrayyy);
        System.out.println(heapyy);
    }
}

/*
Initial Heap:
|   ┌── 3
└── 10
    |   ┌── 1
    └── 5
        └── 4


Heap after pushing:
|       ┌── 10
|   ┌── 15
|   │   └── 3
└── 20
    |   ┌── 1
    └── 12
        |   ┌── 3
        └── 5
            └── 4


Pop Testing:
|       ┌── 3
|   ┌── 10
|   │   └── 3
└── 15
    |   ┌── 1
    └── 12
        └── 5
            └── 4


|       ┌── 3
|   ┌── 10
|   │   └── 3
└── 12
    |   ┌── 1
    └── 5
        └── 4


-----------------MinHeap Testing------------------

Heapy after pushing:
|       ┌── 20
|   ┌── 3
|   │   └── 15
└── 1
    |   ┌── 10
    └── 3
        |   ┌── 5
        └── 4
            └── 12


Pop Testing:
|       ┌── 20
|   ┌── 3
|   │   └── 15
└── 3
    |   ┌── 10
    └── 4
        └── 5
            └── 12


|       ┌── 20
|   ┌── 12
|   │   └── 15
└── 3
    |   ┌── 10
    └── 4
        └── 5


12
3
*/
