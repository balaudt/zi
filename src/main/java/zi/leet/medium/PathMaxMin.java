package zi.leet.medium;

import java.util.Arrays;

//https://leetcode.com/problems/path-with-maximum-minimum-value/
public class PathMaxMin {
    int[][] memo, A;
    int R, C;
    int result = Integer.MIN_VALUE;

    public int maximumMinimumPath(int[][] A) {
        R = A.length;
        C = A[0].length;
        this.A = A;
        memo = new int[R][C];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                memo[i][j] = -1;
            }
        }
        memo[R - 1][C - 1] = A[R - 1][C - 1];
        traverse(0, 0);
        return memo[0][0];
    }

    private int traverse(int r, int c) {
        System.out.println(Arrays.toString(new Object[]{"in", r, c}));
        if (memo[r][c] != -1)
            return memo[r][c];
        if (A[r][c] < result) {
            memo[r][c] = A[r][c];
            return A[r][c];
        }
        memo[r][c] = r < R - 1 ? traverse(r + 1, c) : -1;
        memo[r][c] = Math.max(memo[r][c], c < C - 1 ? traverse(r, c + 1) : -1);
        memo[r][c] = Math.min(memo[r][c], A[r][c]);
        System.out.println(Arrays.toString(new Object[]{"out", r, c, memo[r][c]}));
        return memo[r][c];
    }

    public static void main(String[] args) {
        PathMaxMin pathMaxMin = new PathMaxMin();
//        System.out.println(pathMaxMin.maximumMinimumPath(new int[][]{{5, 4, 5}, {1, 2, 6}, {7, 4, 6}}));
        System.out.println(pathMaxMin.maximumMinimumPath(new int[][]{{2, 2, 1, 2, 2, 2}, {1, 2, 2, 2, 1, 2}}));
    }
}
