import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;

//import java.util.Timer;

public class gridGui extends JFrame {
    private Grid grid;
    private RobotAgent agent;
    private JButton colorButton;

    public gridGui(Grid grid, int goalX, int goalY, int startX, int startY, List<Node> nodeList) {
        this.grid = grid;
        agent = new RobotAgent(grid, startX, startY);

        // Set up the JFrame
        setTitle("Grid GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold the grid components
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(grid.getNumRows(), grid.getNumCols()));

        NodeComponent[][] nodeComponents = new NodeComponent[grid.getNumRows()][grid.getNumCols()];

        for (int row = 0; row < grid.getNumRows(); row++) {
            for (int col = 0; col < grid.getNumCols(); col++) {
                Node node = grid.getNode(row, col);
                NodeComponent nodeComponent = new NodeComponent(node);
                nodeComponents[row][col] = nodeComponent;
                gridPanel.add(nodeComponent);
            }
        }

        add(gridPanel);
        colorButton = new JButton("Start Search");
        add(colorButton, BorderLayout.SOUTH);
        colorButton.addActionListener(new ActionListener() {
            int row = 0;
            int col = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                Node node = grid.getNode(row, col);

                for (int i = 0; i < nodeList.size(); i++) {

                    int nodeIndex = getNodeIndex(nodeList, goalX, goalY, i);

                    NodeComponent nodeComponent = (NodeComponent) gridPanel.getComponent(nodeIndex);
                    /// nodeComponent.setColor(Color.BLUE);

                    Timer timer = new Timer(100 * i, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            nodeComponent.setColor(Color.BLUE);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();

                }

            }
        });

        pack();
        setVisible(true);
    }

    public int getNodeIndex(List<Node> nodeList, int x, int y, int i) {
        int count = 0;

        Node node = nodeList.get(i);
        count = node.getY() * 11 + node.getX();
        return count;

        // Node not found
    }
}
