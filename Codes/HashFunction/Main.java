class Main {
    public static void main(String[] args) {
        String[] words = "Hello my name is Sean Macmillan, and I am bad at generating fake data!".split(" ");
        HashFunction[] funcs1 = { new Division(4), new Extraction(3) };
        HashFunction[] funcs2 = { new Folding(3, true), new Folding(4, false)};
        HashFunction[] funcs3 = { new MidSquare(5) };
        Hashmap h1 = new Hashmap(10, funcs1);
        Hashmap h2 = new Hashmap(10, funcs2);
        Hashmap h3 = new Hashmap(10, funcs3);
        for (String w : words) {
            h1.insert(w);
            h2.insert(w);
            h3.insert(w);
        }
        System.out.println(h1);
        System.out.println(h2);
        System.out.println(h3);
    }
}

/*
 Expected Output:
    [my,bad,data!;;Hello,name,generating;;I,fake;;is,Sean,Macmillan,;;and,am,at;]
    [I,fake;my,is;Hello,at;Sean;bad;Macmillan,,am;generating;;name;and,data!]
    [;name,is,and;;at;Sean,data!;Hello,am;generating,fake;;bad;my,Macmillan,,I]
 */
