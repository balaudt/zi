package zi.leet.medium;

import java.util.*;

//https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph
public class ConnectedComponents {
    BitSet visited;
    Map<Integer, List<Integer>> adjacencyList;

    public int countComponents(int n, int[][] edges) {
        visited = new BitSet(n);
        adjacencyList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        int compCount = 0;
        while (visited.cardinality() != n) {
            compCount++;
            dfs(visited.nextClearBit(0));
        }
        return compCount;
    }

    private void dfs(int node) {
        if (visited.get(node))
            return;
        visited.set(node);
        adjacencyList.get(node).forEach(this::dfs);
    }
}
