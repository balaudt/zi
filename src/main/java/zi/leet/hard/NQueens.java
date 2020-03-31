package zi.leet.hard;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/n-queens/
public class NQueens {
    private boolean[][] queens;
    private List<List<String>> results = new ArrayList<>();
    private int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        queens = new boolean[n][n];
        backtrack(0);
        return results;
    }

    private void backtrack(int row) {
        if (row == n) {
            List<String> result = new ArrayList<>();
            for (boolean[] queen : queens) {
                char[] rowStr = new char[n];
                for (int j = 0; j < queen.length; j++)
                    if (queen[j])
                        rowStr[j] = 'Q';
                    else
                        rowStr[j] = '.';
                result.add(new String(rowStr));
            }
            results.add(result);
        }
        for (int i = 0; i < n; i++) {
            boolean canPlace = true;
            for (int j = 0; j < row && canPlace; j++) {
                int dist = row - j;
                if (queens[j][i]) canPlace = false;
                if (i - dist >= 0 && queens[j][i - dist]) canPlace = false;
                if (i + dist < n && queens[j][i + dist]) canPlace = false;
            }
            if (canPlace) {
                queens[row][i] = true;
                backtrack(row + 1);
                queens[row][i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new NQueens().solveNQueens(4));
    }
}
