import java.util.ArrayList;
import java.util.List;

public class Grid {
    private Node[][] nodes;
    private int numRows;
    private int numCols;

    public Grid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.nodes = new Node[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                this.nodes[i][j] = new Node(j, i);
            }
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public Node getNode(int x, int y) {
        if (x >= 0 && x < numRows && y >= 0 && y < numCols) {
            return nodes[x][y];
        } else {
            return null;
        }
    }

    public void setNode(int x, int y, Node node) {
        if (x >= 0 && x < numRows && y >= 0 && y < numCols) {
            nodes[x][y] = node;
        }
    }

    public void setNodeToNull(int x, int y) {

        this.nodes[x][y] = null;

    }

    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int x = node.getX();

        int y = node.getY();
        Node neighbor;

        // check north neighbor
        if (y > 0) {
            neighbor = this.nodes[y - 1][x];
            if (neighbor != null) {
                neighbors.add(neighbor);
            }
        }

        // check east neighbor
        if (x > 0) {
            neighbor = this.nodes[y][x - 1];
            if (neighbor != null) {
                neighbors.add(neighbor);
            }
        }

        // check south neighbor
        if ((y < this.numRows - 1)) {
            neighbor = this.nodes[y + 1][x];
            if (neighbor != null) {
                neighbors.add(neighbor);
            }
        }

        // check west neighbor
        if (x < this.numCols - 1) {
            neighbor = this.nodes[y][x + 1];
            if (neighbor != null) {
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

}