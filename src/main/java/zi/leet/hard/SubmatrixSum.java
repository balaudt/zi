package zi.leet.hard;

import java.util.Arrays;

//https://leetcode.com/problems/number-of-submatrices-that-sum-to-target
public class SubmatrixSum {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int R = matrix.length, C = matrix[0].length;
        int result = 0;
        int[][][][] sums = new int[R][C][C][R];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sums[r][c][0][0] = matrix[r][c];
                if (matrix[r][c] == target) {
                    result++;
                    System.out.println(Arrays.toString(new int[]{r, c, 0, 0}));
                }
            }
        }
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                for (int w = 0; w < C - c; w++) {
                    for (int h = 0; h < R - r; h++) {
                        if (w == 0 && h == 0) continue;
                        if (w > 0)
                            sums[r][c][w][h] = sums[r][c][w - 1][h] + sums[r][c + 1][0][h];
                        else
                            sums[r][c][w][h] = sums[r][c][w][h - 1] + sums[r + 1][c][w][0];
                        if (sums[r][c][w][h] == target) {
                            result++;
                            System.out.println(Arrays.toString(new int[]{r, c, w, h}));
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SubmatrixSum submatrixSum = new SubmatrixSum();
//        System.out.println(submatrixSum.numSubmatrixSumTarget(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0));
//        System.out.println(submatrixSum.numSubmatrixSumTarget(new int[][]{{1, -1}, {-1, 1}}, 0));
        System.out.println(submatrixSum.numSubmatrixSumTarget(new int[][]{{0, 0, 0, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 1, 1}}, 0));
    }
}
