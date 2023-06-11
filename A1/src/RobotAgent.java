import java.util.*;

public class RobotAgent {
    private Grid grid;
    private Node current;
    private Stack<Node> stack;

    public RobotAgent(Grid grid, int startX, int startY) {
        this.grid = grid;
        this.current = grid.getNode(startY, startX);
        this.stack = new Stack<Node>();
    }

    public List<Node> depthFirstSearch(List<Integer> goalXList, List<Integer> goalYList) {

        stack.push(current);
        List<Node> path = new ArrayList<>();
        path.add(current);
        List<Node> consideredNodes = new ArrayList<>();

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            consideredNodes.add(node);
            for (int i = 0; i < goalXList.size(); i++) {
                int goalX = goalXList.get(i);
                int goalY = goalYList.get(i);

                if (node.getX() == goalX && node.getY() == goalY) {

                    printPath(path);
                    for (int k = 0; k < 5; k++) {
                        for (int j = 0; j < 11; j++) {
                            Node Newnode = grid.getNode(k, j);
                            if (Newnode != null) {
                                ResetNodes(Newnode);

                            }

                        }
                    }

                    System.out.println("Total considered nodes: " + consideredNodes.size());
                    return consideredNodes;
                }
            }

            if (!node.isVisited()) {
                node.setVisited(true);
                boolean foundNextNode = false;
                for (Node neighbor : grid.getNeighbors(node)) {

                    if (neighbor != null && !neighbor.isVisited()) {

                        stack.push(neighbor);
                        path.add(neighbor);

                        if (neighbor.getY() < node.getY()) {

                            foundNextNode = true;
                            break;
                        } else if (neighbor.getX() < node.getX()) {

                            foundNextNode = true;
                            break;
                        } else if (neighbor.getY() > node.getY()) {

                            foundNextNode = true;
                            break;
                        } else if (neighbor.getX() > node.getX()) {

                            foundNextNode = true;
                            break;
                        }
                    }
                }
                if (!foundNextNode) {
                    path.remove(node);
                    if (!path.isEmpty()) {
                        Node prevNode = path.get(path.size() - 1);
                        prevNode.setVisited(false);
                        stack.push(prevNode);

                    }
                }

            }
        }

        System.out.println("Goal not found");
        System.out.println("Total considered nodes: " + consideredNodes.size());
        return consideredNodes;
    }

    public List<Node> breadthFirstSearch(List<Integer> goalXList, List<Integer> goalYList) {
        List<Node> path = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(current);
        List<Node> consideredNodes = new ArrayList<>();

        current.setVisited(false);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            consideredNodes.add(node);
            for (int i = 0; i < goalXList.size(); i++) {
                int goalX = goalXList.get(i);
                int goalY = goalYList.get(i);

                if (node.getX() == goalX && node.getY() == goalY) {

                    while (node != null) {
                        path.add(node);
                        node = node.getParent();
                    }
                    Collections.reverse(path);
                    printPath(path);

                    for (int k = 0; k < 5; k++) {
                        for (int j = 0; j < 11; j++) {
                            Node Newnode = grid.getNode(k, j);
                            if (Newnode != null) {
                                ResetNodes(Newnode);
                            }

                        }
                    }

                    System.out.println("Total considered nodes: " + consideredNodes.size());
                    return consideredNodes;
                }
            }

            if (!node.isVisited()) {
                node.setVisited(true);

                for (Node neighbor : grid.getNeighbors(node)) {

                    if (neighbor != null && !neighbor.isVisited()) {

                        queue.add(neighbor);
                        if (neighbor.getParent() == null) {
                            neighbor.setParent(node);

                        }

                    }
                }
            }
        }

        System.out.println("Goal not found");
        System.out.println("Total considered nodes: " + consideredNodes.size());
        return consideredNodes;
    }

    public List<Node> greedyBestFirstSearch(List<Integer> goalXList, List<Integer> goalYList) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(node -> node.getH()));
        frontier.add(current);
        List<Node> consideredNodes = new ArrayList<>();

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            consideredNodes.add(node);
            for (int i = 0; i < goalXList.size(); i++) {
                int goalX = goalXList.get(i);
                int goalY = goalYList.get(i);
                if (node.getX() == goalX && node.getY() == goalY) {

                    System.out.println("Total considered nodes: " + consideredNodes.size());
                    List<Node> path = new ArrayList<>();
                    while (node != null) {
                        path.add(node);
                        node = node.getParent();
                    }
                    Collections.reverse(path);
                    printPath(path);
                    for (int k = 0; k < 5; k++) {
                        for (int j = 0; j < 11; j++) {
                            Node Newnode = grid.getNode(k, j);
                            if (Newnode != null) {
                                ResetNodes(Newnode);
                            }

                        }
                    }
                    return consideredNodes;
                }
            }

            if (!node.isVisited()) {
                node.setVisited(true);
                for (Node neighbor : grid.getNeighbors(node)) {
                    if (neighbor != null && !neighbor.isVisited()) {

                        neighbor.setH(heuristic(neighbor.getX(), neighbor.getY(), goalXList, goalYList));

                        // neighbor.setParent(node);

                        frontier.add(neighbor);
                        if (neighbor.getParent() == null) {
                            neighbor.setParent(node);
                        }
                    }
                }
            }
        }

        System.out.println("Goal not found");
        System.out.println("Total considered nodes: " + consideredNodes.size());
        return consideredNodes;
    }

    public List<Node> aStarSearch(List<Integer> goalXList, List<Integer> goalYList) {
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(node -> node.getF()));
        frontier.add(current);
        List<Node> consideredNodes = new ArrayList<>();
        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            consideredNodes.add(node);
            for (int i = 0; i < goalXList.size(); i++) {
                int goalX = goalXList.get(i);
                int goalY = goalYList.get(i);
                if (node.getX() == goalX && node.getY() == goalY) {

                    System.out.println("Total considered nodes: " + consideredNodes.size());
                    List<Node> path = new ArrayList<>();
                    while (node != null) {
                        path.add(node);
                        node = node.getParent();
                    }
                    Collections.reverse(path);
                    printPath(path);
                    for (int k = 0; k < 5; k++) {
                        for (int j = 0; j < 11; j++) {
                            Node Newnode = grid.getNode(k, j);
                            if (Newnode != null) {
                                ResetNodes(Newnode);
                                Newnode.setG(0);
                                Newnode.setH(0);
                                Newnode.setF(0);
                                Newnode.setCost(0);
                            }

                        }
                    }
                    return consideredNodes;
                }
            }

            if (!node.isVisited()) {
                node.setVisited(true);
                for (Node neighbor : grid.getNeighbors(node)) {
                    if (neighbor != null && !neighbor.isVisited()) {
                        // neighbor.setParent(node);
                        neighbor.setG(node.getCost() + 1);
                        neighbor.setH(heuristic(neighbor.getX(), neighbor.getY(), goalXList, goalYList));
                        neighbor.setF(neighbor.getG() + neighbor.getH());
                        frontier.add(neighbor);
                        if (neighbor.getParent() == null) {
                            neighbor.setParent(node);
                        }
                    }
                }
            }
        }

        System.out.println("Goal not found");
        System.out.println("Total considered nodes: " + consideredNodes.size());
        return consideredNodes;
    }

    public List<Node> dijkstraSearch(List<Integer> goalXList, List<Integer> goalYList) {
        LinkedList<Node> frontier = new LinkedList<>();
        current.setCost(0);
        frontier.add(current);
        List<Node> consideredNodes = new ArrayList<>();
        while (!frontier.isEmpty()) {
            // Sort frontier based on cost value
            frontier.sort(Comparator.comparingInt(Node::getCost));

            Node node = frontier.poll();
            consideredNodes.add(node);
            for (int i = 0; i < goalXList.size(); i++) {
                int goalX = goalXList.get(i);
                int goalY = goalYList.get(i);
                if (node.getX() == goalX && node.getY() == goalY) {

                    System.out.println("Total considered nodes: " + consideredNodes.size());
                    List<Node> path = new ArrayList<>();
                    while (node != null) {
                        path.add(node);
                        node = node.getParent();
                    }
                    Collections.reverse(path);
                    printPath(path);
                    for (int k = 0; k < 5; k++) {
                        for (int j = 0; j < 11; j++) {
                            Node Newnode = grid.getNode(i, j);
                            if (Newnode != null) {
                                ResetNodes(Newnode);
                                Newnode.setG(0);
                                Newnode.setH(0);
                                Newnode.setF(0);
                            }
                        }
                    }
                    return consideredNodes;
                }
            }

            if (!node.isVisited()) {
                node.setVisited(true);
                for (Node neighbor : grid.getNeighbors(node)) {
                    if (neighbor != null && !neighbor.isVisited()) {
                        int newCost = node.getCost() + 1;
                        if (newCost > neighbor.getCost()) {
                            neighbor.setCost(newCost);
                        }

                        frontier.add(neighbor);
                        if (neighbor.getParent() == null) {
                            neighbor.setParent(node);
                        }
                    }
                }
            }
        }

        System.out.println("Goal not found");
        System.out.println("Total considered nodes: " + consideredNodes.size());
        return consideredNodes;
    }

    public List<Node> iterativeDeepeningAStar(List<Integer> goalXList, List<Integer> goalYList) {

        int threshold = heuristic(current.getX(), current.getY(), goalXList, goalYList);
        int nextThreshold = Integer.MAX_VALUE;
        List<Node> consideredNodes = new ArrayList<>();
        while (true) {

            LinkedList<Node> frontier = new LinkedList<>();
            frontier.add(current);
            current.setG(0);
            current.setH(heuristic(current.getX(), current.getY(), goalXList, goalYList));
            current.setF(current.getG() + current.getH());
            current.setVisited(false);

            while (!frontier.isEmpty()) {
                Node node = frontier.poll();
                consideredNodes.add(node);
                int f = node.getF();
                for (int i = 0; i < goalXList.size(); i++) {
                    int goalX = goalXList.get(i);
                    int goalY = goalYList.get(i);
                    if (node.getX() == goalX && node.getY() == goalY) {

                        System.out.println("Total considered nodes: " + consideredNodes.size());
                        List<Node> path = new ArrayList<>();
                        while (node != null) {
                            path.add(node);
                            node = node.getParent();
                        }
                        Collections.reverse(path);
                        printPath(path);
                        for (int k = 0; k < 5; k++) {
                            for (int j = 0; j < 11; j++) {
                                Node Newnode = grid.getNode(k, j);
                                if (Newnode != null) {
                                    ResetNodes(Newnode);
                                }
                            }
                        }
                        return consideredNodes;

                    }
                }

                if (f > threshold) {
                    threshold = Math.min(nextThreshold, f);
                    continue;
                }

                if (!node.isVisited()) {
                    node.setVisited(true);
                    for (Node neighbor : grid.getNeighbors(node)) {
                        if (neighbor != null && !neighbor.isVisited()) {

                            neighbor.setG(node.getCost() + 1);
                            neighbor.setH(heuristic(neighbor.getX(), neighbor.getY(), goalXList, goalYList));
                            neighbor.setF(neighbor.getG() + neighbor.getH());
                            int fNeighbor = neighbor.getF();

                            if (fNeighbor <= threshold) {
                                neighbor.setParent(node);

                                neighbor.setF(fNeighbor);
                                frontier.add(neighbor);
                                // break;
                            } else {
                                nextThreshold = Math.min(nextThreshold, fNeighbor);
                            }
                        }
                    }
                }
            }

            if (nextThreshold == Integer.MAX_VALUE) {
                break;
            }

            threshold = nextThreshold;
        }

        System.out.println("Goal not found");
        System.out.println("Total considered nodes: " + consideredNodes.size());
        return consideredNodes;
    }

    private int heuristic(int x, int y, List<Integer> goalXList, List<Integer> goalYList) {
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < goalXList.size(); i++) {
            int goalX = goalXList.get(i);
            int goalY = goalYList.get(i);
            int distance = Math.abs(x - goalX) + Math.abs(y - goalY);
            minDistance = Math.min(minDistance, distance);
        }
        return minDistance;
    }

    public void ResetNodes(Node node) {
        node.setVisited(false);
        node.setParent(null);
    }

    public void printPath(List<Node> path) {

        System.out.print("Path: ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size() - 1; i++) {
            Node currentNode = path.get(i);
            Node nextNode = path.get(i + 1);
            if (nextNode.getY() < currentNode.getY()) {
                sb.append(" up ");
            } else if (nextNode.getX() < currentNode.getX()) {
                sb.append(" left ");
            } else if (nextNode.getY() > currentNode.getY()) {
                sb.append(" down ");
            } else if (nextNode.getX() > currentNode.getX()) {
                sb.append(" right ");
            }

        }
        System.out.println(sb.toString().replaceAll("  ", " , "));

    }

}