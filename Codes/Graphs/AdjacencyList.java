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
        if (containsNode(data)) return;
        nodes.add(new Node<T>(data));
    }

    @Override
    public void addEdge(T dataFrom, T dataTo) {
        System.out.println("Attempting to add edge from " + dataFrom + " to " + dataTo);
        int fromIndex = indexOfNode(dataFrom);
        int toIndex = indexOfNode(dataTo);

        System.out.println("From index: " + fromIndex + ", To index: " + toIndex);

        if (fromIndex == -1 || toIndex == -1) {
            System.out.println("One or both nodes not found.");
            return;
        }

        Node<T> fromNode = nodes.get(fromIndex);
        Node<T> toNode = nodes.get(toIndex);

        for (int i = 0; i < fromNode.edges.size(); i++) {
            Node<T> edge = fromNode.edges.get(i);
            if (edge.data.equals(dataTo)) {
                System.out.println("Edge already exists.");
                return;
            }
        }

        // Edge doesn't exist, add it
        System.out.println("Adding...");
        fromNode.edges.add(toNode);
    }

    public void removeNode(T data) {
        // Find the index of the node to be removed
        int indexToRemove = indexOfNode(data);
        
        // If the node is not found, return
        if (indexToRemove == -1) {
            return;
        }

        // Remove the node from the nodes list
        nodes.removeIndex(indexToRemove);

        // Remove edges pointing to the removed node from other nodes' edge lists
        for (int i = 0; i < nodes.size(); i++) {
            Node<T> node = nodes.get(i);
            // Remove any edges pointing to the removed node
            node.edges.remove(new Node<>(data));
        }
    }

    public void removeEdge(T dataFrom, T dataTo) {
        // Find the indices of the nodes corresponding to dataFrom and dataTo
        int indexFrom = indexOfNode(dataFrom);
        int indexTo = indexOfNode(dataTo);
        
        // If either node is not found, or if an edge does not exist, return
        if (indexFrom == -1 || indexTo == -1) {
            return; // One or both nodes not found
        }
        
        // Get the nodes corresponding to dataFrom and dataTo
        Node<T> nodeFrom = nodes.get(indexFrom);
        Node<T> nodeTo = nodes.get(indexTo);

        // Remove the edge between the two nodes
        nodeFrom.edges.remove(nodeTo);
    }

    @Override
    public int numberOfNodes() {
        return nodes.size();
    }

    @Override
    public int numberOfEdges() {
        int totalEdges = 0;

        // Iterate through each node in the graph
        for (int i = 0; i < nodes.size(); i++) {
            Node<T> node = nodes.get(i);

            // Iterate through the edge list of the current node and count the edges
            totalEdges += node.edges.size();
        }

        // Since each edge in an undirected graph counts as two edges, we divide the total by 2
        return totalEdges / 2;
        // return totalEdges;
    }

    @Override
    public boolean containsNode(T data) {
        return indexOfNode(data) != -1;
    }

    @Override
    public int indexOfNode(T data) {
        Node<T> target = new Node<>(data);
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T nodeAtIndex(int index) {
        return nodes.get(index).data;
    }

    @Override
    public boolean isConnected(T dataFrom, T dataTo) {
        // Check if both nodes are present in the graph
        if (!containsNode(dataFrom) || !containsNode(dataTo)) {
            return false; // One or both nodes not found
        }

        // Get the index of the node corresponding to dataFrom
        int indexFrom = indexOfNode(dataFrom);

        // Get the node corresponding to dataFrom
        Node<T> nodeFrom = nodes.get(indexFrom);

        // Get the index of the node corresponding to dataTo
        int indexTo = indexOfNode(dataTo);

        // Iterate over the indices of nodeFrom's edge list
        for (int i = 0; i < nodeFrom.edges.size(); i++) {
            Node<T> edgeNode = nodeFrom.edges.get(i);
            if (edgeNode.data.equals(dataTo)) {
                return true; // Edge between nodeFrom and nodeTo exists
            }
        }

        // No edge found between nodeFrom and nodeTo
        return false;
    }

    @Override
    public LinkedList<T> getNeighbors(T data) {
        LinkedList<T> neighbors = new LinkedList<>();
        if (!containsNode(data)) {
            System.out.println("Node not found in the graph.");
            return neighbors;
        }
        int index = indexOfNode(data);
        Node<T> node = nodes.get(index);
    
        System.out.print("Neighbors of node " + data + ": ");
        for (int i = 0; i < node.edges.size(); i++) {
            Node<T> edgeNode = node.edges.get(i);
            neighbors.add(edgeNode.data);
        }
        // Print the list of neighbors individually
        if (neighbors.size() == 0) {
            System.out.println("No neighbors found for node " + data);
        } else {
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(neighbors.get(i) + " ");
            }
    }
        System.out.println();
        return neighbors;
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

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node<U> node = (Node<U>) obj;
            return data.equals(node.data);
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }

    }

    // Code yet  to be refactored!!!!!!!!!!!!!!
}
