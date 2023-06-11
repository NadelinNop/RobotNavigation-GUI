import javax.swing.*;
import java.awt.*;

public class NodeComponent extends JComponent {

    private static final int NODE_SIZE = 20;
    private Node node;
    private Color color; // Add this line

    public NodeComponent(Node node) {
        this.node = node;
        setPreferredSize(new Dimension(NODE_SIZE, NODE_SIZE));
        this.color = getColorFromNode(node); // Add this line
    }

    // Add this method
    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw a square with the current color
        int x = 0;
        int y = 0;
        g.setColor(color);
        g.fillRect(x, y, NODE_SIZE, NODE_SIZE);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, NODE_SIZE - 1, NODE_SIZE - 1);
    }

    // Add this method
    private Color getColorFromNode(Node node) {
        if (node == null) {
            return Color.BLACK;
        } else {
            if (node.getX() == 0 && node.getY() == 1) {
                return Color.RED;
            } else if (node.getX() == 7 && node.getY() == 0) {
                return Color.GREEN;
            } else if (node.getX() == 10 && node.getY() == 3) {
                return Color.GREEN;
            } else {
                return Color.WHITE;
            }
        }
    }

}
