package zi.leet.easy;

//https://leetcode.com/problems/surface-area-of-3d-shapes
public class SurfaceAreaOf3D {
    public int surfaceArea(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0)
                    continue;
                sum += grid[i][j] * 4 + 2;
                if (i > 0)
                    sum -= Math.min(grid[i][j], grid[i - 1][j]) * 2;
                if (j > 0)
                    sum -= Math.min(grid[i][j], grid[i][j - 1]) * 2;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        SurfaceAreaOf3D surfaceArea = new SurfaceAreaOf3D();
        System.out.println(surfaceArea.surfaceArea(new int[][]{{2}}));
        System.out.println(surfaceArea.surfaceArea(new int[][]{{1, 2}, {3, 4}}));
        System.out.println(surfaceArea.surfaceArea(new int[][]{{1, 0}, {0, 2}}));
        System.out.println(surfaceArea.surfaceArea(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
        System.out.println(surfaceArea.surfaceArea(new int[][]{{2, 2, 2}, {2, 1, 2}, {2, 2, 2}}));
    }
}
