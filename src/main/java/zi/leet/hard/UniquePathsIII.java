package zi.leet.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/unique-paths-iii
public class UniquePathsIII {
    private Map<int[], Integer> memo = new HashMap<>();
    private int source, target, R, C, allVisited;
    private Set<Integer> obstacles = new HashSet<>();

    public int uniquePathsIII(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int cell = i * C + j;
                switch (grid[i][j]) {
                    case 2:
                        target = cell;
                        break;
                    case -1:
                        obstacles.add(cell);
                        break;
                    case 1:
                        source = cell;
                    default:
                        allVisited |= 1 << cell;
                }
            }
        }
        return ways(source, 0);
    }

    private int ways(int cell, int visited) {
        int cellOn = 1 << cell;
        if ((visited & cellOn) > 0) return 0;
        if (obstacles.contains(cell))
            return 0;
        if (cell == target)
            return visited == allVisited ? 1 : 0;
        int[] key = {cell, visited};
        if (memo.containsKey(key))
            return memo.get(key);
        int r = cell / C;
        int c = cell % C;
        int result = 0;
        visited |= cellOn;
        if (r > 0) result += ways(cell - C, visited);
        if (r < R - 1) result += ways(cell + C, visited);
        if (c > 0) result += ways(cell - 1, visited);
        if (c < C - 1) result += ways(cell + 1, visited);
        memo.put(key, result);
        return result;
    }


    public static void main(String[] args) {
        UniquePathsIII paths = new UniquePathsIII();
        System.out.println(paths.uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}}));
        System.out.println(paths.uniquePathsIII(new int[][]{{0, 1}, {2, 0}}));
    }
}
