
public class DirectedGraph<T> extends Graph<T> {

    public DirectedGraph(GraphImplementation<T> impl) {
        super(impl);
    }

    @Override
    public void addEdge(T from, T to) {
        // Add edge from 'from' to 'to'
        impl.addEdge(from, to);
        // Since it's an undirected graph, add edge from 'to' to 'from' as well
        impl.addEdge(to, from);
    }

    @Override
    public void removeEdge(T from, T to) {
        // Remove edge from 'from' to 'to'
        impl.removeEdge(from, to);
        // Also, remove edge from 'to' to 'from'
        impl.removeEdge(to, from);
    }

}
