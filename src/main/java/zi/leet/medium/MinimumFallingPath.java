package zi.leet.medium;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/minimum-falling-path-sum
public class MinimumFallingPath {
    private int[][] in;
    private Map<Map.Entry<Integer, Integer>, Integer> memo;

    public int minFallingPathSum(int[][] A) {
        this.in = A;
        memo = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < in[0].length; i++) {
            min = Math.min(min, minFallingPathSum(i, 1) + in[0][i]);
        }
        return min;
    }

    private int minFallingPathSum(int prevRowCell, int row) {
        if (row == in.length) return 0;
        AbstractMap.SimpleEntry<Integer, Integer> key = new AbstractMap.SimpleEntry<>(prevRowCell, row);
        if (memo.containsKey(key))
            return memo.get(key);
        int min = Integer.MAX_VALUE;
        if (prevRowCell > 0)
            min = Math.min(min, in[row][prevRowCell - 1] + minFallingPathSum(prevRowCell - 1, row + 1));
        min = Math.min(min, in[row][prevRowCell] + minFallingPathSum(prevRowCell, row + 1));
        if (prevRowCell < in[row].length - 1)
            min = Math.min(min, in[row][prevRowCell + 1] + minFallingPathSum(prevRowCell + 1, row + 1));
        memo.put(key, min);
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumFallingPath().minFallingPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }
}
