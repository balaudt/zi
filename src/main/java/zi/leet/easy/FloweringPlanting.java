package zi.leet.easy;

import java.util.*;

//https://leetcode.com/problems/flower-planting-with-no-adjacent
public class FloweringPlanting {
    private int[] solution;
    private Map<Integer, List<Integer>> adjList;

    public int[] gardenNoAdj(int N, int[][] paths) {
        solution = new int[N];
        adjList = new HashMap<>();
        for (int[] path : paths) {
            buildAdjacency(path, true);
            buildAdjacency(path, false);
        }
        solve(0);
        return solution;
    }

    private void buildAdjacency(int[] edge, boolean reverse) {
        int node1 = reverse ? edge[0] : edge[1];
        int node2 = reverse ? edge[1] : edge[0];
        node1--;
        node2--;
        if (adjList.containsKey(node1)) {
            adjList.get(node1).add(node2);
        } else {
            ArrayList<Integer> adjacents = new ArrayList<>();
            adjacents.add(node2);
            adjList.put(node1, adjacents);
        }
    }

    private void solve(int ind) {
        if (ind == solution.length) {
            return;
        }
        Set<Integer> possibleColors = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        if (adjList.containsKey(ind)) {
            adjList.get(ind).stream().filter(adjacent -> adjacent < ind).forEach(adjacent -> possibleColors.remove(solution[adjacent]));
            solution[ind] = possibleColors.iterator().next();
        } else {
            solution[ind] = 1;
        }
        solve(ind + 1);
    }

    public static void main(String[] args) {
        FloweringPlanting planting = new FloweringPlanting();
        System.out.println(Arrays.toString(planting.gardenNoAdj(3, new int[][]{{1, 2}, {2, 3}, {3, 1}})));
        System.out.println(Arrays.toString(planting.gardenNoAdj(4, new int[][]{{1, 2}, {3, 4}})));
        System.out.println(Arrays.toString(planting.gardenNoAdj(4, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 3}, {2, 4}})));
    }
}
