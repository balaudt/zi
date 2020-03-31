package zi.leet.medium;

//https://leetcode.com/problems/score-after-flipping-matrix
public class FlipMatrix {
    public int matrixScore(int[][] A) {
        int result = A.length * (1 << A[0].length - 1);
        for (int i = 0; i < A.length; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < A[i].length; j++)
                    A[i][j] = A[i][j] == 0 ? 1 : 0;
            }
        }
        for (int i = 1; i < A[0].length; i++) {
            int oneCount = 0;
            for (int j = 0; j < A.length; j++) oneCount += A[j][i] == 1 ? 1 : 0;
//            if (oneCount < A.length / 2) oneCount = A.length - oneCount;
            oneCount = Math.max(oneCount, A.length - oneCount);
            result += (1 << A[0].length - i - 1) * oneCount;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new FlipMatrix().matrixScore(new int[][]{{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}}));
    }
}
