import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParseTextFile {

    private String grid;
    private Grid gridMap;
    private List<Integer> goalXList = new ArrayList<>();
    private List<Integer> goalYList = new ArrayList<>();

    private int startX;
    private int startY;
    private int goalX1;
    private int goalY1;
    private int goalX2;
    private int goalY2;
    private String startCell;
    private String goalStates;
    private String wall;

    public List<Integer> getgoalXList() {
        return goalXList;
    }

    public List<Integer> getgoalYList() {
        return goalYList;
    }

    public String getGrid() {
        return grid;
    }

    public String getstartCell() {
        return startCell;
    }

    public int getstartX() {
        return startX;
    }

    public int getstartY() {
        return startY;
    }

    public int getgoalX1() {
        return goalX1;
    }

    public int getgoalY1() {
        return goalY1;
    }

    public int getgoalX2() {
        return goalX2;
    }

    public int getgoalY2() {
        return goalY2;
    }

    public String getgoalStates() {
        return goalStates;
    }

    public Grid getgridMap() {
        return gridMap;
    }

    public void populateData(String filename) {
        int counter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
                if (counter == 0) {
                    grid = line;
                    ParseGrid(grid);
                }
                if (counter == 1) {
                    startCell = line;
                    ParseStarting(startCell);
                }
                if (counter == 2) {
                    goalStates = line;
                    ParseGoals(goalStates);
                }
                if (counter >= 3) {
                    wall = line;
                    ParseWalls(wall, counter);

                }

                counter += 1;

            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public void ParseGrid(String lineGrid) {

        lineGrid = lineGrid.replace("[", "").replace("]", "");
        String[] bothDimensions = lineGrid.split(",");
        int mapWidth = Integer.parseInt(bothDimensions[0].trim());
        int mapHeight = Integer.parseInt(bothDimensions[1].trim());
        gridMap = new Grid(mapWidth, mapHeight);

    }

    public void ParseStarting(String lineStart) {
        lineStart = lineStart.replace("(", "").replace(")", "");
        String[] bothDimensions = lineStart.split(",");
        startX = Integer.parseInt(bothDimensions[0].trim());
        startY = Integer.parseInt(bothDimensions[1].trim());
    }

    public void ParseGoals(String lineGoal) {

        String[] parts = lineGoal.split("\\s*\\|\\s*");

        for (String part : parts) {
            // Remove the parentheses and split the coordinates
            String coord = part.replace("(", "").replace(")", "");
            String[] coords = coord.split(",");

            // Parse the X and Y coordinates and add them to the lists
            int goalX = Integer.parseInt(coords[0].trim());
            int goalY = Integer.parseInt(coords[1].trim());
            goalXList.add(goalX);
            goalYList.add(goalY);
        }

    }

    public void ParseWalls(String wallLine, int counter) {

        wallLine = wallLine.replace("(", "").replace(")", "");
        String[] bothDimensions = wallLine.split(",");
        int wallX = Integer.parseInt(bothDimensions[0].trim());
        int wallY = Integer.parseInt(bothDimensions[1].trim());
        int wallWidth = Integer.parseInt(bothDimensions[2].trim());
        int wallHeight = Integer.parseInt(bothDimensions[3].trim());
        gridMap.setNodeToNull(wallY, wallX);

        if (wallWidth > 1 && ((wallHeight == 1) || (wallHeight == 2))) {
            for (int i = 1; i < wallWidth; i++) {
                wallX = wallX + 1;
                gridMap.setNodeToNull(wallY, wallX);

            }

        }
        if (wallHeight > 1 && ((wallWidth == 1) || (wallWidth == 2))) {
            for (int i = 1; i < wallHeight; i++) {
                wallY = wallY + 1;
                gridMap.setNodeToNull(wallY, wallX);

            }

        }
        if (wallHeight == 2 & wallWidth == 2) {
            wallX = wallX - 1;
            gridMap.setNodeToNull(wallY, wallX);

        }

    }

}
