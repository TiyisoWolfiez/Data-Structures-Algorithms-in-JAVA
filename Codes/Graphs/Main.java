public class Main {
    public static void main(String[] args) {
        AdjacencyListTest();
        System.err.println("----------------------Test 1 Done---------------------");
        AdjacencyMatrixTest();
        System.err.println("----------------------Test 2 Done---------------------");
    }

    static void AdjacencyListTest() {
        // Create an instance of AdjacencyList
        AdjacencyList<String> graph = new AdjacencyList<>();

        // Add nodes to the graph
        graph.addNode("A");
        System.err.println(graph.display());
        System.err.println(graph.indexOfNode("A"));

        graph.addNode("B");
        System.err.println(graph.display());


        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        // Add edges to the graph
        System.out.println("About to add some Edges:");
        graph.addEdge("A", "B");
        System.out.println(graph.display());

        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");

        // Display the graph structure
        // System.out.println("Graph Structure:");
        System.out.println(graph.display());
        graph.getNeighbors("B");

        // Test removing a node
        graph.removeNode("C");

        // Display the updated graph structure
        System.out.println("Graph Structure after removing node C:");
        System.out.println(graph.display());

        // Test removing an edge
        graph.removeEdge("B", "D");

        // Display the updated graph structure
        System.out.println("Graph Structure after removing edge between B and D:");
        System.out.println(graph.display());

        // Test getting neighbors of a node
        graph.getNeighbors("A");

        // Test checking connectivity
        System.out.println("Is node A connected to node B? " + graph.isConnected("A", "B"));

        // Test counting nodes and edges
        System.out.println("Number of nodes in the graph: " + graph.numberOfNodes());
        System.out.println("Number of edges in the graph: " + graph.numberOfEdges());
    }

    static void AdjacencyMatrixTest() {
        // Create an instance of the graph
        AdjacencyMatrix<String> graph = new AdjacencyMatrix<>();

        // Add nodes to the graph
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        // Add edges to the graph
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "A");
        graph.addEdge("A", "C");
        graph.addEdge("C", "C");
        System.err.println(graph.display());

        // Test number of nodes and edges
        System.out.println("Number of nodes: " + graph.numberOfNodes()); // Should be 4
        System.out.println("Number of edges: " + graph.numberOfEdges()); // Should be 5

        // Test containsNode method
        System.out.println("Contains node 'A': " + graph.containsNode("A")); // Should be true
        System.out.println("Contains node 'E': " + graph.containsNode("E")); // Should be false

        // Test isConnected method
        System.out.println("Is connected 'A' to 'B': " + graph.isConnected("A", "B")); // Should be true
        System.out.println("Is connected 'B' to 'D': " + graph.isConnected("B", "D")); // Should be false

        // Test getNeighbors method
        graph.getNeighbors("A"); // Should be [B, C]
        graph.getNeighbors("D"); // Should be [A]

        // Test removeNode method
        graph.removeNode("C");
        System.out.println("Graph after removing node 'C':\n" + graph.display());

        // Test removeEdge method
        graph.removeEdge("A", "B");
        System.out.println("Graph after removing edge 'A' to 'B':\n" + graph.display());
    }
}
