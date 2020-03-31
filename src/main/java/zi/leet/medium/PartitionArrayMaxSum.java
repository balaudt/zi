package zi.leet.medium;

//https://leetcode.com/problems/partition-array-for-maximum-sum
public class PartitionArrayMaxSum {
    int[] in;
    int k;
    int memo[];

    public int maxSumAfterPartitioning(int[] A, int K) {
        in = A;
        k = K;
        memo = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            memo[i] = -1;
        }
        return traverse(0);
    }

    private int traverse(int st) {
        if (st >= in.length) return 0;
        if (memo[st] != -1)
            return memo[st];
        int result = -1, max = -1;
        int end = Math.min(st + k, in.length);
        for (int i = st; i < end; i++) {
            max = Math.max(max, in[i]);
            result = Math.max(result, traverse(i + 1) + max * (i - st + 1));
        }
        memo[st] = result;
        return result;
    }
}
