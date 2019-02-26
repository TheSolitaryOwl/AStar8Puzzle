import java.util.ArrayList;

/**
 * Zachary Rowton
 * Prof Brian McLaughlan
 * CS 3113 AI
 * A* 8-Puzzle
 */

// Import statements

public class Main
{
    private static int[][] startState = {{5, 2, 4}, {1, 0, 8}, {3, 6, 7}};
    private static int goalState[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    private static int gx = 0;
    private static ArrayList<Node> openList = new ArrayList<>();
    private static ArrayList<Node> closedList = new ArrayList<>();

    public static void main (String[] args)
    {
        // Initialize
        openList.add(new Node(startState, gx + 1));

        // Solve puzzle using the A* algorithm
        aStar();
    }

    private static void aStar ()
    {
        while (!openList.isEmpty())
        {
            Node currentNode = getNodeWithLowestFx();
            if (currentNode.isGoal())
            {
                println("We have found the goal");
                break;
            }
            else
            {

            }
        }
    }

    private static Node getNodeWithLowestFx ()
    {
        Node returnNode;
        int indexOfLowestFx = 0;
        int lowestFx = Integer.MAX_VALUE;
        for (Node node : openList)
        {
            if (node.getFx() < lowestFx)
            {
                lowestFx = node.getFx();
                indexOfLowestFx = openList.indexOf(node);
            }
        }
        returnNode = openList.get(indexOfLowestFx);

        return returnNode;
    }




    // Helper functions
    private static void print (String string) { System.out.print(string); }

    private static void println (String string) { System.out.println(string); }
}
