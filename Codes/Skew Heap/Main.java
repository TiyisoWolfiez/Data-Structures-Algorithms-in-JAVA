public class Main {
    public static void main(String[] args) {
        System.out.println("---------------------Test 1----------------------");
        test1();

        System.out.println();
        System.out.println("---------------------Test 2----------------------");
        System.out.println();

        test2();
    }
    
    static void test1() {
        String inputString = "{100{50{75{80{12{}{}}{}}{60{}{}}}{25{}{}}}{150{175{190{26{}{}}{}}{160{}{}}}{125{110{}{}}{130{}{}}}}}";
        MaxSkewHeap skewHeap = new MaxSkewHeap(inputString);

        System.out.println(skewHeap);
        System.out.println();

        skewHeap.insert(90);
        skewHeap.insert(55);
        skewHeap.insert(55);

        System.out.println("After Insertions: ");
        System.out.println(skewHeap);
        System.out.println();

        skewHeap.remove(55);
        skewHeap.remove(100);
        System.out.println("After removals: ");
        System.out.println(skewHeap);
        System.out.println();

        System.out.println("Search Test: ");
        System.out.println(skewHeap.search(60));
        System.out.println();

        System.out.println("Search Path for 12: ");
        System.out.println(skewHeap.searchPath(12));

        System.out.println("Search Path for 1000: ");
        System.out.println(skewHeap.searchPath(1000));
        System.out.println();

        System.out.println("isLeftist: ");
        System.out.println(skewHeap.isLeftist());
        System.out.println();
        
        System.out.println("isRightist: ");
        System.out.println(skewHeap.isRightist());
        System.out.println();
    }

    static void test2() {
        MaxSkewHeap skewHeap = new MaxSkewHeap();

        skewHeap.insert(100);
        skewHeap.insert(123);
        skewHeap.insert(122);
        skewHeap.insert(150);
        skewHeap.insert(75);
        skewHeap.insert(80);
        skewHeap.insert(160);
        skewHeap.insert(25);
        skewHeap.insert(12);
        skewHeap.insert(65);
        skewHeap.insert(200);
        skewHeap.insert(50);
        skewHeap.insert(1);

        System.out.println("After Insertion: ");
        System.out.println(skewHeap);
        System.out.println();

        System.out.println("Search Path for 12: ");
        System.out.println(skewHeap.searchPath(12));
        System.out.println();

        System.out.println("Search Path for 25: ");
        System.out.println(skewHeap.searchPath(25));
        System.out.println();

        System.out.println("isLeftist: ");
        System.out.println(skewHeap.isLeftist());
        System.out.println();
        System.out.println("isRightist: ");
        System.out.println(skewHeap.isRightist());
        System.out.println();

        skewHeap.remove(200);
        skewHeap.remove(1);
        skewHeap.remove(65);
        skewHeap.remove(80);

        System.out.println("After Removal: ");
        System.out.println(skewHeap);
        System.out.println();

        System.out.println("Search Path for 1: ");
        System.out.println(skewHeap.searchPath(1));
        System.out.println();

        System.out.println("Search Path for 75: ");
        System.out.println(skewHeap.searchPath(75));
        System.out.println();

        System.out.println(skewHeap.isLeftist());
        System.out.println(skewHeap.isRightist());
        System.out.println();
    }
}


/* 
 Expected Output:


│           ┌── 130
│       ┌── 125
│       │   └── 110
│   ┌── 150
│   │   │   ┌── 160
│   │   └── 175
│   │       └── 190
│   │           └── 26
└── 100
    │   ┌── 25
    └── 50
        │   ┌── 60
        └── 75
            └── 80
                └── 12
 

After Insertions:
│           ┌── 160
│       ┌── 175
│       │   └── 190
│       │       └── 26
│   ┌── 150
│   │   │   ┌── 110
│   │   └── 125
│   │       └── 130
│   │           └── 90
└── 100
    └── 55
        │   ┌── 25
        └── 50
            │   ┌── 60
            └── 75
                └── 80
                    └── 12

After removals:
│       ┌── 110
│   ┌── 125
│   │   └── 130
│   │       └── 90
└── 150
    │   ┌── 190
    │   │   └── 26
    └── 175
        └── 160
            │   ┌── 25
            └── 50
                │   ┌── 60
                └── 75
                    └── 80
                        └── 12





---------------------Test 1----------------------
│           ┌── 130
│       ┌── 125
│       │   └── 110
│   ┌── 150
│   │   │   ┌── 160
│   │   └── 175
│   │       └── 190
│   │           └── 26
└── 100
    │   ┌── 25
    └── 50
        │   ┌── 60
        └── 75
            └── 80
                └── 12


After Insertions:
│           ┌── 160
│       ┌── 175
│       │   └── 190
│       │       └── 26
│   ┌── 150
│   │   │   ┌── 110
│   │   └── 125
│   │       └── 130
│   │           └── 90
└── 100
    └── 55
        │   ┌── 25
        └── 50
            │   ┌── 60
            └── 75
                └── 80
                    └── 12


After removals:
│       ┌── 110
│   ┌── 125
│   │   └── 130
│   │       └── 90
└── 150
    │   ┌── 190
    │   │   └── 26
    └── 175
        └── 160
            │   ┌── 25
            └── 50
                │   ┌── 60
                └── 75
                    └── 80
                        └── 12


Search Test:
null

Search Path for 12:
150->125->110->130->90->175->190->26->160->50->25->75->60->80->[12]
Search Path for 1000:
150

isLeftist:
true

isRightist:
false


---------------------Test 2----------------------

After Insertion:
│   ┌── 50
└── 200
    │   ┌── 65
    │   │   └── 25
    └── 160
        │   ┌── 75
        │   │   └── 12
        └── 150
            │   ┌── 100
            │   │   └── 80
            └── 123
                └── 122
                    └── 1


Search Path for 12:
200->50->160->65->25->150->75->[12]

Search Path for 25:
200->50->160->65->[25]

isLeftist:
true

isRightist:
false

After Removal:
│       ┌── 75
│       │   └── 12
│   ┌── 150
│   │   │   ┌── 100
│   │   └── 123
│   │       └── 122
└── 160
    └── 50
        └── 25


Search Path for 1:
160->150->75->12->123->100->122->50->25

Search Path for 75:
160->150->[75]

false
false
 
 */
