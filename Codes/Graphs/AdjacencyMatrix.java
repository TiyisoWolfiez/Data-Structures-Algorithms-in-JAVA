
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
        nodes.add(data);
        
        // Resize the matrix
        int size = nodes.size();
        boolean[][] newMatrix = new boolean[size][size];
        
        // Copy the existing data from the old matrix to the new matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        matrix = newMatrix;
    }

    @Override
    public void addEdge(T dataFrom, T dataTo) {
        if (!containsNode(dataFrom) || !containsNode(dataTo)) {
            System.err.println("One or both nodes not found in the graph.");
            return;
        }
        int indexFrom = nodes.indexOf(dataFrom);
        int indexTo = nodes.indexOf(dataTo);
        
        matrix[indexFrom][indexTo] = true;
    }

    public void removeNode(T data) {
        int indexToRemove = nodes.indexOf(data);
        
        // If the node is not found, return
        if (indexToRemove == -1) {
            return;
        }
        nodes.removeIndex(indexToRemove);
    
        // Remove the corresponding row and column from the matrix
        removeRowAndColumn(matrix, indexToRemove);
    
        // Update the indices of the nodes in the matrix
        updateIndices(indexToRemove);
    }
    
    // Method to remove a row and a column from the matrix
    private void removeRowAndColumn(boolean[][] matrix, int index) {
        int size = matrix.length;
    
        // Create a new matrix with reduced size
        boolean[][] newMatrix = new boolean[size - 1][size - 1];
    
        // Copy elements to the new matrix, skipping the row and column at the specified index
        int newRow = 0;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                continue; // Skip the row at the specified index
            }
            int newCol = 0;
            for (int j = 0; j < size; j++) {
                if (j == index) {
                    continue; // Skip the column at the specified index
                }
                newMatrix[newRow][newCol++] = matrix[i][j];
            }
            newRow++;
        }
        // Update the original matrix with the new matrix
        this.matrix = newMatrix;
    }
    
    // Method to update the indices of the nodes in the matrix after removal
    private void updateIndices(int removedIndex) {
        int size = matrix.length;
    
        // Shift indices of nodes after the removed node
        for (int i = removedIndex; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                matrix[i][j] = matrix[i + 1][j];
            }
        }
    
        // Shift indices of nodes after the removed node
        for (int j = removedIndex; j < size - 1; j++) {
            for (int i = 0; i < size - 1; i++) {
                matrix[i][j] = matrix[i][j + 1];
            }
        }
    }    

    public void removeEdge(T dataFrom, T dataTo) {
        if (!containsNode(dataFrom) || !containsNode(dataTo)) {
            System.err.println("One or both nodes not found in the graph.");
            return;
        }

        int indexFrom = nodes.indexOf(dataFrom);
        int indexTo = nodes.indexOf(dataTo);
        
        matrix[indexFrom][indexTo] = false;
    }

    @Override
    public int numberOfNodes() {
        return nodes.size();
    }

    @Override
    public int numberOfEdges() {
        int edgeCount = 0;
        // Iterate over the adjacency matrix and count the edges
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]) {
                    edgeCount++;
                }
            }
        }
        // Divide the edge count by 2 to account for undirected edges
        return edgeCount / 2;
    }

    @Override
    public boolean containsNode(T data) {
        return nodes.indexOf(data) != -1;
    }

    @Override
    public int indexOfNode(T data) {
        int i = 0;
        for (; i < nodes.size(); i++) {
            if (nodes.get(i).equals(data)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T nodeAtIndex(int index) {
        if (index < 0 || index >= nodes.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + nodes.size());
        }
        return nodes.get(index);
    }

    @Override
    public boolean isConnected(T dataFrom, T dataTo) {
        // Check if both nodes are present in the graph
        if (!containsNode(dataFrom) || !containsNode(dataTo)) {
            return false; // One or both nodes not found
        }
        
        // Get the indices of the nodes corresponding to dataFrom and dataTo
        int indexFrom = indexOfNode(dataFrom);
        int indexTo = indexOfNode(dataTo);
        
        // Check if there's a connection between the nodes in the adjacency matrix
        return matrix[indexFrom][indexTo];
    }

    @Override
    public LinkedList<T> getNeighbors(T data) {
        LinkedList<T> neighbors = new LinkedList<>();
    
        // Check if the node with the given data is present in the graph
        if (!containsNode(data)) {
            System.out.println("Node not found in the graph.");
            return neighbors; // Return empty list if node not found
        }
        
        // Get the index of the node corresponding to the given data
        int index = indexOfNode(data);
        
        // Iterate through the row in the adjacency matrix corresponding to the node
        System.out.print("Neighbors of node " + data + ": ");
        for (int i = 0; i < nodes.size(); i++) {
            if (matrix[index][i]) {
                neighbors.add(nodeAtIndex(i)); // Add neighbor node's data to the list
            }
        }

        for(int i = 0; i < neighbors.size(); i+=1) {
            System.out.print(neighbors.get(i) + " ");
        }

        System.out.println();
        return neighbors;
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
