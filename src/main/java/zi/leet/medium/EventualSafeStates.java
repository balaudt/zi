package zi.leet.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/find-eventual-safe-states
public class EventualSafeStates {
    boolean[] unsafeNodes;
    boolean[] masterVisited;
    Set<Integer> unvisited;
    int n;
    int[][] adj;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        n = graph.length;
        adj = graph;
        unsafeNodes = new boolean[n];
        masterVisited = new boolean[n];
        unvisited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            unvisited.add(i);
        }
        Set<Integer> path = new HashSet<>();
        while (!unvisited.isEmpty()) {
            visit(unvisited.iterator().next(), path);
        }
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (!unsafeNodes[i]) results.add(i);
        return results;
    }

    private void visit(int node, Set<Integer> path) {
        if (masterVisited[node]) {
            if (path.contains(node) || unsafeNodes[node])
                path.forEach(n -> unsafeNodes[n] = true);
            return;
        }
        unvisited.remove(node);
        masterVisited[node] = true;
        path.add(node);
        for (int neighbour : adj[node]) {
            visit(neighbour, path);
        }
        path.remove(node);
    }

    public static void main(String[] args) {
        EventualSafeStates states = new EventualSafeStates();
        System.out.println(states.eventualSafeNodes(new int[][]{{0}, {2, 3, 4}, {3, 4}, {0, 4}, {}}));
    }
}
