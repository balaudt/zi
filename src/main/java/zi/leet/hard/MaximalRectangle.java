package zi.leet.hard;

//https://leetcode.com/problems/maximal-rectangle
public class MaximalRectangle {
    private int R, C;
    private int[][] rowWise;
    private int result = Integer.MIN_VALUE;

    public int maximalRectangle(char[][] matrix) {
        R = matrix.length;
        if (R == 0) return 0;
        C = matrix[0].length;
        if (C == 0) return 0;
        rowWise = new int[R][C];
        for (int i = 0; i < R; i++) {
            rowWise[i][C - 1] = matrix[i][C - 1] == '1' ? 1 : 0;
            for (int j = C - 2; j >= 0; j--) {
                rowWise[i][j] = matrix[i][j] == '1' ? 1 + rowWise[i][j + 1] : 0;
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                maxInRect(i, j);
            }
        }
        return result;
    }

    private void maxInRect(int r, int c) {
        if (r >= R || c >= C) return;
        int colCt = rowWise[r][c], rowCt = 1;
        result = Math.max(result, colCt);
        for (int i = r + 1; i < R && rowWise[i][c] != 0; i++) {
            rowCt++;
            colCt = Math.min(colCt, rowWise[i][c]);
            result = Math.max(result, colCt * rowCt);
        }
    }

    public static void main(String[] args) {
        MaximalRectangle rectangle = new MaximalRectangle();
        System.out.println(rectangle.maximalRectangle(new char[][]{"10100".toCharArray(), "10111".toCharArray(),
                "11111".toCharArray(), "10010".toCharArray()}));
    }
}
