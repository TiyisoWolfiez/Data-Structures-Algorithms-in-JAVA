class AdjacencyList<T> implements GraphImplementation<T> {

    public LinkedList<Node<T>> nodes;

    /*
     * Constructor
     */
    public AdjacencyList() {
        this.nodes = new LinkedList<>();
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
        String out = ("Graph structure:\n");
        for (int i = 0; i < nodes.size(); i++) {
            Node<T> currentNode = nodes.get(i);

            StringBuilder displayString = new StringBuilder("\t" + currentNode.data.toString() + ": ");
            LinkedList<Node<T>> edges = currentNode.getEdges();

            for (int j = 0; j < edges.size(); j++) {
                displayString.append(edges.get(j).data.toString());
                if (j < edges.size() - 1) {
                    displayString.append(", ");
                }
            }

            out += (displayString + "\n");
        }
        return out;
    }

    /*
     * Private methods used for this implementation
     */

    /*
     * Inner nodes used for this implementation
     */
    private class Node<U> {
        public U data;
        public LinkedList<Node<U>> edges;

        public Node(U data) {
            this.data = data;
            this.edges = new LinkedList<>();
        }

        public LinkedList<Node<U>> getEdges() {
            return this.edges;
        }

        public void addEdge(Node<U> newConnection) {
            this.edges.add(newConnection);
        }

        public void removeEdge(Node<U> oldConnection) {
            this.edges.remove(oldConnection);
        }

    }
}
