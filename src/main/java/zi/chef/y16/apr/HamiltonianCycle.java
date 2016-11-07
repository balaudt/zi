package zi.chef.y16.apr;

/**
 * *   Java Program to Implement Hamiltonian Cycle Algorithm
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Class HamiltonianCycle
 **/
/* Java program for solution of Hamiltonian Cycle problem
   using backtracking */
public class HamiltonianCycle {
    int V;
    int path[];

    /* A utility function to check if the vertex v can be
       added at index 'pos'in the Hamiltonian Cycle
       constructed so far (stored in 'path[]') */
    boolean isSafe(int v, int graph[][], int path[], int pos) {
        /* Check if this vertex is an adjacent vertex of
           the previously added vertex. */
        if (graph[path[pos - 1]][v] == 0)
            return false;

        /* Check if the vertex has already been included.
           This step can be optimized by creating an array
           of size V */
        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;

        return true;
    }

    /* A recursive utility function to solve hamiltonian
       cycle problem */
    boolean hamCycleUtil(int graph[][], int path[], int pos) {
        /* base case: If all vertices are included in
           Hamiltonian Cycle */
        if (pos == V) {
            // And if there is an edge from the last included
            // vertex to the first vertex
            if (graph[path[pos - 1]][path[0]] == 1)
                return true;
            else
                return false;
        }

        // Try different vertices as a next candidate in
        // Hamiltonian Cycle. We don't try for 0 as we
        // included 0 as starting point in in hamCycle()
        for (int v = 1; v < V; v++) {
            /* Check if this vertex can be added to Hamiltonian
               Cycle */
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;

                /* recur to construct rest of the path */
                if (hamCycleUtil(graph, path, pos + 1) == true)
                    return true;

                /* If adding vertex v doesn't lead to a solution,
                   then remove it */
                path[pos] = -1;
            }
        }

        /* If no vertex can be added to Hamiltonian Cycle
           constructed so far, then return false */
        return false;
    }

    /* This function solves the Hamiltonian Cycle problem using
       Backtracking. It mainly uses hamCycleUtil() to solve the
       problem. It returns false if there is no Hamiltonian Cycle
       possible, otherwise return true and prints the path.
       Please note that there may be more than one solutions,
       this function prints one of the feasible solutions. */
    int hamCycle(int graph[][]) {
        path = new int[V];
        for (int i = 0; i < V; i++)
            path[i] = -1;

        /* Let us put vertex 0 as the first vertex in the path.
           If there is a Hamiltonian Cycle, then the path can be
           started from any point of the cycle as the graph is
           undirected */
        path[0] = 0;
        if (hamCycleUtil(graph, path, 1) == false) {
            System.out.println("\nSolution1 does not exist");
            return 0;
        }

        printSolution(path);
        return 1;
    }

    /* A utility function to print solution */
    void printSolution(int path[]) {
        System.out.println("Solution1 Exists: Following" +
                " is one Hamiltonian Cycle");
        for (int i = 0; i < V; i++)
            System.out.print(" " + path[i] + " ");

        // Let us print the first vertex again to show the
        // complete cycle
        System.out.println(" " + path[0] + " ");
    }

    // driver program to test above function
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("/Users/balaudt/Temp/apr/hamcycle5.in"));
        System.out.println("HamiltonianCycle Algorithm Test\n");
        /** Make an object of HamiltonianCycle class **/
        HamiltonianCycle hc = new HamiltonianCycle();

        /** Accept number of vertices **/
        System.out.println("Enter number of vertices\n");
        int V = hc.V = scan.nextInt();

        /** get graph **/
        System.out.println("\nEnter matrix\n");
        int[][] graph = new int[V][V];
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                graph[i][j] = scan.nextInt();

        hc.hamCycle(graph);

        hc.V = 2;
        hc.hamCycle(new int[][]{{0, 1}, {1, 0}});
    }


    public static void main1(String[] args) {
        int n = 1, m = 4;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < m; l++) {
                        System.out.print(Math.abs(i - k) + Math.abs(j - l) == 1 ? 1 : 0);
                        if (k != n - 1 || l != m - 1)
                            System.out.print(' ');
                    }
                }
                System.out.println();
            }
        }
    }
}
