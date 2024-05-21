
public class AdjacencyMatrix<T> implements GraphImplementation<T> {
    public LinkedList<T> nodes; // Stores the nodes to maintain index mapping
    public boolean[][] matrix; // Adjacency matrix to store edge information

    /*
     * Constructor
     */
    public AdjacencyMatrix() {
        nodes = new LinkedList<>();
        matrix = new boolean[0][0];
    }

    /*
     * Public methods to adhere to the interface
     */
    @Override
    public void addNode(T data) {

    }

    @Override
    public void addEdge(T dataFrom, T dataTo) {

    }

    public void removeNode(T data) {

    }

    public void removeEdge(T dataFrom, T dataTo) {

    }

    @Override
    public int numberOfNodes() {

    }

    @Override
    public int numberOfEdges() {

    }

    @Override
    public boolean containsNode(T data) {

    }

    @Override
    public int indexOfNode(T data) {

    }

    @Override
    public T nodeAtIndex(int index) {

    }

    @Override
    public boolean isConnected(T dataFrom, T dataTo) {

    }

    @Override
    public LinkedList<T> getNeighbors(T data) {

    }

    @Override
    public String display() {
        String out = ("Adjacency Matrix:\n");
        for (int i = 0; i < matrix.length; i++) {
            out += "\t" + nodes.get(i) + ": ";
            for (int j = 0; j < matrix[i].length; j++) {
                out += (matrix[i][j] ? "1 " : "0 ");
            }
            out += "\n";
        }
        return out;
    }

    /*
     * Private methods used for this implementation
     */

}
