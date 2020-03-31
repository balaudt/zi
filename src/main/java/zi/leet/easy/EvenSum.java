package zi.leet.easy;

//https://leetcode.com/problems/sum-of-even-numbers-after-queries
public class EvenSum {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] out = new int[queries.length];
        int evenSum = 0;
        for (int i = 0; i < A.length; i++) {
            evenSum += A[i] % 2 == 0 ? A[i] : 0;
        }
        for (int i = 0; i < queries.length; i++) {
            int val = queries[i][0];
            int index = queries[i][1];
            if (A[index] % 2 == 0) evenSum -= A[index];
            A[index] += val;
            if (A[index] % 2 == 0) evenSum += A[index];
            out[i] = evenSum;
        }
        return out;
    }
}
