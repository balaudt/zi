package zi.leet.easy;

//https://leetcode.com/problems/flipping-an-image
public class FlipImage {
    public int[][] flipAndInvertImage(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) return A;
        int rowLength = A[0].length;
        for (int i = 0; i < A.length; i++) {
            int end = rowLength / 2;
            if (A[i].length % 2 != 0) {
                A[i][end] = A[i][end] == 0 ? 1 : 0;
                end -= 1;
            }
            for (int j = 0; j < rowLength / 2; j++) {
                int t = A[i][j];
                A[i][j] = A[i][rowLength - 1 - j] == 0 ? 1 : 0;
                A[i][rowLength - 1 - j] = t == 0 ? 1 : 0;
            }
        }
        return A;
    }
}
