package zi.leet.easy;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//https://leetcode.com/problems/rotting-oranges
public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        Queue<Map.Entry<Integer, Integer>> q = new LinkedList<>();
        int rottenCount = 0, orangeCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0)
                    orangeCount++;
                if (grid[i][j] == 2) {
                    q.offer(new AbstractMap.SimpleEntry<>(i, j));
                    rottenCount++;
                }
            }
        }
        int minutes = 0;
        while (rottenCount < orangeCount && !q.isEmpty()) {
            Queue<Map.Entry<Integer, Integer>> next = new LinkedList<>();
            for (Map.Entry<Integer, Integer> entry : q) {
                int r = entry.getKey();
                int c = entry.getValue();
                int[][] neighbours = new int[][]{{r - 1, c}, {r + 1, c}, {r, c - 1}, {r, c + 1}};
                for (int[] neighbour : neighbours) {
                    if (neighbour[0] < 0 || neighbour[0] >= grid.length || neighbour[1] < 0 || neighbour[1] >= grid[0].length)
                        continue;
                    if (grid[neighbour[0]][neighbour[1]] == 1) {
                        grid[neighbour[0]][neighbour[1]] = 2;
                        rottenCount++;
                        next.offer(new AbstractMap.SimpleEntry<>(neighbour[0], neighbour[1]));
                    }
                }
            }
            q = next;
            minutes++;
        }
        return rottenCount == orangeCount ? minutes : -1;
    }

    public static void main(String[] args) {
        RottingOranges rottingOranges = new RottingOranges();
        System.out.println(rottingOranges.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
    }
}
