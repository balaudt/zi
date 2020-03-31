package zi.leet.medium;

//https://leetcode.com/problems/minimum-path-sum
public class MinimumPathSum {
    private int[][] memo, in;
    private int R, C;

    public int minPathSum(int[][] grid) {
        in = grid;
        R = grid.length;
        C = grid[0].length;
        memo = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                memo[i][j] = -1;
            }
        }
        return min(0, 0);
    }

    private int min(int r, int c) {
        if (r == R - 1 && c == C - 1) return in[r][c];
        if (memo[r][c] != -1) return memo[r][c];
        int result = r != R - 1 ? min(r + 1, c) : Integer.MAX_VALUE;
        result = Math.min(result, c != C - 1 ? min(r, c + 1) : Integer.MAX_VALUE);
        result += in[r][c];
        memo[r][c] = result;
        return result;
    }

    public static void main(String[] args) {
        MinimumPathSum pathSum = new MinimumPathSum();
        System.out.println(pathSum.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }
}
