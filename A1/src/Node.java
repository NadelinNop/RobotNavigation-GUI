
public class Node {
    private int x;
    private int y;
    private boolean visited;
    private NodeComponent nodeComponent;
    private Node parent;
    private int h;
    private int g;
    private int f;
    private int cost;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;

        parent = null;
        this.h = 0;
        this.g = 0;
        this.cost = Integer.MAX_VALUE;

    }

    public void setComponent(NodeComponent nodeComponent) {
        this.nodeComponent = nodeComponent;
    }

    public NodeComponent getComponent() {
        return nodeComponent;
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getCost() {
        return cost;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost(Node neighbor) {
        if (neighbor == null) {
            return Integer.MAX_VALUE;
        }
        return 1;
    }

}
