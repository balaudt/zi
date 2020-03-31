package zi.leet.medium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/graph-valid-tree
public class ValidTree {

    public boolean validTree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjList.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            BitSet visited = new BitSet();
            Queue<Map.Entry<Integer, Integer>> q = new ArrayDeque<>();
            q.add(new AbstractMap.SimpleEntry<>(i, -1));
            boolean isPossible = true;
            while (!q.isEmpty()) {
                Map.Entry<Integer, Integer> entry = q.poll();
                Integer node = entry.getKey();
                if (visited.get(node)) {
                    isPossible = false;
                    break;
                }
                visited.set(node);
                q.addAll(adjList.get(node).stream()
                        .filter(x -> !x.equals(entry.getValue()))
                        .map(x -> new AbstractMap.SimpleEntry<>(x, node))
                        .collect(Collectors.toList()));
            }
            if (isPossible && visited.cardinality() == n)
                return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        ValidTree validTree = new ValidTree();
        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/balamurugan/Temp/1.txt")));
        String line = null;
        List<int[]> edges = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            int source = Integer.parseInt(line);
            int dest = Integer.parseInt(reader.readLine());
            edges.add(new int[]{source, dest});
        }
        int[][] edgeArray = new int[edges.size()][];
        edges.toArray(edgeArray);
        System.out.println(validTree.validTree(2000, edgeArray));

        System.out.println(validTree.validTree(3, new int[][]{{0, 1}, {1, 2}}));
    }
}
