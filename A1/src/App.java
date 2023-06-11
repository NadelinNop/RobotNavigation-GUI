
public class App {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java App <file-path> <search-method>");
            return;
        }

        String filePath = args[0];
        String searchMethod = args[1];

        ParseTextFile parse = new ParseTextFile();
        parse.populateData(filePath);

        RobotAgent robot = new RobotAgent(parse.getgridMap(), parse.getstartX(), parse.getstartY());
        System.out.println(searchMethod + ": ");

        switch (searchMethod) {
            case "DFS":
                System.out.println(filePath);

                gridGui DFSgui = new gridGui(parse.getgridMap(), parse.getgoalX1(), parse.getgoalY1(),
                        parse.getstartX(), parse.getstartY(),
                        robot.depthFirstSearch(parse.getgoalXList(), parse.getgoalYList()));

                break;
            case "BFS":
                System.out.println(filePath);

                gridGui BFSgui = new gridGui(parse.getgridMap(), parse.getgoalX1(), parse.getgoalY1(),
                        parse.getstartX(), parse.getstartY(),
                        robot.breadthFirstSearch(parse.getgoalXList(), parse.getgoalYList()));
                break;
            case "GBFS":
                System.out.println(filePath);
                gridGui GBFSgui = new gridGui(parse.getgridMap(), parse.getgoalX1(), parse.getgoalY1(),
                        parse.getstartX(), parse.getstartY(),
                        robot.greedyBestFirstSearch(parse.getgoalXList(), parse.getgoalYList()));
                break;
            case "AS":
                System.out.println(filePath);

                gridGui ASgui = new gridGui(parse.getgridMap(), parse.getgoalX1(), parse.getgoalY1(), parse.getstartX(),
                        parse.getstartY(), robot.aStarSearch(parse.getgoalXList(), parse.getgoalYList()));
                break;
            case "Dijkstra":
                System.out.println(filePath);

                gridGui Dijgui = new gridGui(parse.getgridMap(), parse.getgoalX1(), parse.getgoalY1(),
                        parse.getstartX(), parse.getstartY(),
                        robot.dijkstraSearch(parse.getgoalXList(), parse.getgoalYList()));
                break;
            case "IDAS":
                System.out.println(filePath);

                gridGui IDAgui = new gridGui(parse.getgridMap(), parse.getgoalX1(), parse.getgoalY1(),
                        parse.getstartX(), parse.getstartY(),
                        robot.iterativeDeepeningAStar(parse.getgoalXList(), parse.getgoalYList()));
                break;
            default:
                System.out.println("Invalid search method: " + searchMethod);
                break;
        }
    }

}