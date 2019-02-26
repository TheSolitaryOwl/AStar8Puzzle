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

    public static void main (String[] args)
    {
        new Node(startState);
    }




    // Helper functions
    private static void print (String string) { System.out.print(string); }

    private static void println (String string) { System.out.println(string); }
}
