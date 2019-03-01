/*
 * Zachary Rowton
 * Prof Brian McLaughlan
 * CS 3113 AI
 * A* 8-Puzzle
 */

// Import statements
import java.util.ArrayList;
import java.util.Random;

public class Main
{
    //private static int[][] startState = {{5, 2, 4}, {1, 0, 8}, {3, 6, 7}};
    private static int[][] startState = generateStartState();
    private static int goalState[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    private static int gx = 1;
    private static ArrayList<Node> openList = new ArrayList<>();
    private static ArrayList<Node> closedList = new ArrayList<>();

    enum MoveDirections
    {
        UP, DOWN, LEFT, RIGHT;
    }

    public static void main (String[] args)
    {
        // Initialize
        openList.add(new Node(startState, gx));
        printState(startState);

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
            else // If we haven't found the goal
            {
                openList.remove(currentNode);
                closedList.add(currentNode);

                for (MoveDirections direction : MoveDirections.values())
                {
                    Node up, down, left, right;

                    switch (direction) {
                        case UP:
                            int[][] upState = swapTiles (currentNode.getCurrentState(), direction);
                            if (upState !=null)
                            {
                                up = new Node(upState, gx);
                            }
                            break;
                        case DOWN:
                            int[][] downState = swapTiles (currentNode.getCurrentState(), direction);
                            if (downState !=null)
                            {
                                down = new Node(downState, gx);
                            }
                            break;
                        case LEFT:
                            int[][] leftState = swapTiles(currentNode.getCurrentState(), direction);
                            if (leftState !=null)
                            {
                                left = new Node(leftState, gx);
                            }
                            break;
                        case RIGHT:
                            int[][] rightState = swapTiles(currentNode.getCurrentState(), direction);
                            if (rightState != null)
                            {
                                right = new Node(rightState, gx);
                            }
                            break;
                    }
                }
            }
        }
    }

    private static int[][] swapTiles(int[][] state, MoveDirections direction)
    {
        int[][] newState = copyMatrix(state);
        int[] emptyPos = new int[2];
        emptyPos = findEmpty(newState);
        switch (direction)
        {
            case UP:
                if (emptyPos[0] > 0)
                {
                    int temp = newState[emptyPos[0] - 1][emptyPos[1]];
                    newState[emptyPos[0] - 1][emptyPos[1]] = newState[emptyPos[0]][emptyPos[1]];
                    newState[emptyPos[0]][emptyPos[1]] = temp;
                }
                else
                {
                    newState = null;
                }
                break;
            case DOWN:
                if (emptyPos[0] < state.length)
                {
                    int temp = newState[emptyPos[0] + 1][emptyPos[1]];
                    newState[emptyPos[0] + 1][emptyPos[1]] = newState[emptyPos[0]][emptyPos[1]];
                    newState[emptyPos[0]][emptyPos[1]] = temp;
                }
                else
                {
                    newState = null;
                }
                break;
            case LEFT:
                if (emptyPos[1] > 0)
                {
                    int temp = newState[emptyPos[0]][emptyPos[1] - 1];
                    newState[emptyPos[0]][emptyPos[1] - 1] = newState[emptyPos[0]][emptyPos[1]];
                    newState[emptyPos[0]][emptyPos[1]] = temp;
                }
                else
                {
                    newState = null;
                }
                break;
            case RIGHT:
                if (emptyPos[1] < state[0].length)
                {
                    int temp = newState[emptyPos[0]][emptyPos[1] +1];
                    newState[emptyPos[0]][emptyPos[1] + 1] = newState[emptyPos[0]][emptyPos[1]];
                    newState[emptyPos[0]][emptyPos[1]] = temp;
                }
                else
                {
                    newState = null;
                }
                break;
        }
        return newState;
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

    private static int[][] generateStartState()
    {
        int[][] state = new int[3][3];
        int size = 9;
        Random random = new Random();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++)
            numbers.add(i);
        for (int i = 0; i < state.length; i++)
        {
            for (int j = 0; j < state[i].length; j++)
            {
                int index = random.nextInt(size);
                state[i][j] = numbers.get(index);
                numbers.remove(index);
                size--;
            }
        }
        return state;
    }




    // Helper functions
    private static void print (String string) { System.out.print(string); }

    private static void println (String string) { System.out.println(string); }

    private static void printState (int[][] state)
    {
        for (int i = 0; i < state.length; i++)
        {
            for (int j = 0; j < state[i].length; j++)
            {
                int currentNum = state[i][j];
                if (currentNum == 0)
                    System.out.print("  ");
                else
                    System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\n");
    }

    // for copying two-dimensional arrays to avoid manipulating the original
    private static int[][] copyMatrix(int[][] stateToBeCopied)
    {
        int[][] state = new int[stateToBeCopied.length][stateToBeCopied[0].length];
        for (int i = 0; i < state.length; i++)
        {
            for (int j = 0; j < state[i].length; j++)
            {
                state[i][j] = stateToBeCopied[i][j];
            }
        }
        return state;
    }

    private static int[] findEmpty (int[][] state)
    {
        int[] pos = new int[2];
        for (int i = 0; i < state.length; i++)
        {
            for (int j = 0; j < state[i].length; j++)
            {
                if (state[i][j] == 0)
                {
                    pos[0] = j;
                    pos[1] = i;
                }
            }
        }
        return pos;
    }
}
