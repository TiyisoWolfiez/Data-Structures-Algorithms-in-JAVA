public abstract class Graph<T> {
    public GraphImplementation<T> impl;

    Graph(GraphImplementation<T> impl) {
        this.impl = impl;
    }

    public void addNode(T node) {

    }

    abstract void addEdge(T from, T to);

    public void removeNode(T node) {

    }

    abstract void removeEdge(T from, T to);

    public void display() {

    }

    public LinkedList<T> bfs(T startNode) {

    }

    public LinkedList<T> dfs(T startNode) {

    }

    public LinkedList<T> unweightedShortestPath(T startNode, T endNode) {

    }
}
