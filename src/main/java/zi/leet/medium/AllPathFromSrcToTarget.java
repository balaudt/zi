package zi.leet.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/all-paths-from-source-to-target
public class AllPathFromSrcToTarget {
    private int[][] adjList;
    private List<List<Integer>> results;
    private int n;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        adjList = graph;
        n = adjList.length;
        results = new ArrayList<>();
        visit(0, new Stack<>());
        return results;
    }

    private void visit(int node, Stack<Integer> path) {
        if (node == n - 1) {
            ArrayList<Integer> result = new ArrayList<>(path);
            result.add(n - 1);
            results.add(result);
            return;
        }
        path.push(node);
        for (int neighbour : adjList[node]) {
            visit(neighbour, path);
        }
        path.pop();
    }

    public static void main(String[] args) {
        System.out.println(new AllPathFromSrcToTarget().allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}}));
    }
}
