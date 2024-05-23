public abstract class Graph<T> {
    public GraphImplementation<T> impl;

    Graph(GraphImplementation<T> impl) {
        this.impl = impl;
    }

    public void addNode(T node) {
        impl.addNode(node);
    }

    abstract void addEdge(T from, T to);

    public void removeNode(T node) {
        impl.removeNode(node);
    }

    abstract void removeEdge(T from, T to);

    public void display() {
        System.out.println(impl.display());
    }

    public LinkedList<T> bfs(T startNode) {
        LinkedList<T> visited = new LinkedList<>();
        LinkedList<T> queue = new LinkedList<>();
        queue.add(startNode);
        visited.add(startNode);

        while (queue.size() != 0) {
            T currentNode = queue.get(0); // Get the first element in the queue
            queue.removeIndex(0); // Remove the first element from the queue manually
            LinkedList<T> neighbors = getNeighbors(currentNode);
            for (int i = 0; i < neighbors.size(); i++) {
                T neighbor = neighbors.get(i);
                if (!containsNode(visited, neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visited;        
    }

    public LinkedList<T> getNeighbors(T data) {
        return impl.getNeighbors(data);
    }

    private boolean containsNode(LinkedList<T> visited, T node) {
        for (int i = 0; i < visited.size(); i++) {
            if (visited.get(i).equals(node)) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<T> dfs(T startNode) {
        LinkedList<T> visited = new LinkedList<>();
        dfsRecursive(startNode, visited);
        return visited;
    }

    private void dfsRecursive(T node, LinkedList<T> visited) {
        visited.add(node); // Mark the current node as visited
        LinkedList<T> neighbors = getNeighbors(node);
        for (int i = 0; i < neighbors.size(); i++) {
            T neighbor = neighbors.get(i);
            if (!containsNode(visited, neighbor)) {
                dfsRecursive(neighbor, visited); // Recursively visit unvisited neighbors
            }
        }
    }
    

    public LinkedList<T> unweightedShortestPath(T startNode, T endNode) {
        LinkedList<T> shortestPath = new LinkedList<>();
        LinkedList<T> visited = new LinkedList<>();
        Object[] parentMapKeys = new Object[impl.numberOfNodes()];
        Object[] parentMapValues = new Object[impl.numberOfNodes()];
    
        LinkedList<T> queue = new LinkedList<>();
        queue.add(startNode);
        visited.add(startNode);
        parentMapKeys[indexOfNode(startNode)] = startNode; // Start node has no parent
    
        while (queue.size() != 0) {
            queue.removeIndex(0);
            T currentNode = queue.get(0); // Remove the first element from the queue
            if (currentNode.equals(endNode)) {
                // Reconstruct the shortest path
                T node = endNode;
                while (node != null) {
                    shortestPath.addFirst(node); // Add nodes to the front of the list
                    int index = indexOfNode(node);
                    node = (T) parentMapValues[index];
                }
                return shortestPath;
            }
    
            LinkedList<T> neighbors = getNeighbors(currentNode);
            for (int i = 0; i < neighbors.size(); i++) {
                T neighbor = neighbors.get(i);
                if (!containsNode(visited, neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    int index = indexOfNode(neighbor);
                    parentMapKeys[index] = neighbor; // Track the parent of the neighbor
                    parentMapValues[index] = currentNode;
                }
            }
        }
    
        // If endNode is not reachable from startNode
        System.out.println("No path found between " + startNode + " and " + endNode);
        return shortestPath; // Returns an empty list
    }    

    private int indexOfNode(T data) {
        return impl.indexOfNode(data);
    }  
}
