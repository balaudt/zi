package zi.leet.easy;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/matrix-cells-in-distance-order
public class MatrixCellsInDistanceOrder {
    boolean[][] visited;
    List<int[]> out;

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        visited = new boolean[R][C];
        out = new ArrayList<>();
        visit(r0, c0);
        return out.toArray(new int[][]{});
    }

    private void visit(int r, int c) {
        if (r < 0 || c < 0 || r == visited.length || c == visited[0].length)
            return;
        if (visited[r][c])
            return;
        out.add(new int[]{r, c});
        visit(r - 1, c);
        visit(r + 1, c);
        visit(r, c - 1);
        visit(r, c + 1);
    }
}
