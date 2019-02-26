import java.util.Vector;

public class Node
{
    private int gx; // path cost from start node to this node
    private int hx; // path cost from this node to goal
    private int fx; // g(x) + h(x) = f(x)
    private Node parent;
    private int goalState[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    private int currentState[][];

    enum Moves
    {
        UP, DOWN, LEFT, RIGHT;
    }

    Node (int[][] currentState)
    {
        this.currentState = currentState;
        System.out.println(calculateHx());
    }

    //TODO method for calculating hx
    private int calculateHx ()
    {
        int xDis = 0;
        int yDis = 0;
        int goalPos[]; //x y

        for (int i = 0; i < currentState.length; i++)
        {
            for (int j = 0; j < currentState[i].length; j++)
            {
                int currentNum = currentState[i][j];
                goalPos = findPos(currentNum);
                xDis = Math.abs(j - goalPos[0]);
                yDis = Math.abs(i - goalPos[1]);
            }
        }
        return xDis + yDis;
    }

    private int[] findPos (int n)
    {
        int[] pos = new int[2];
        for (int i = 0; i < goalState.length; i++)
        {
            for (int j = 0; j < goalState[i].length; j++)
            {
                if (goalState[i][i] == n)
                {
                    pos[0] = j;
                    pos[1] = i;
                }
            }
        }
        return pos;
    }

    //TODO method for calculating gx

    private void printState (int[][] state)
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
}
