package zi.leet.hard;

//https://leetcode.com/problems/burst-balloons
public class BurstBallons {
    private int[][] memo;
    private int[] in;

    public int maxCoins(int[] nums) {
        int n = nums.length + 2;
        in = new int[n];
        in[0] = in[n - 1] = 1;
        System.arraycopy(nums, 0, in, 1, n - 2);
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        return compute(1, n - 2);
    }

    private int compute(int l, int r) {
        if (l == r) return in[l];
        if (memo[l][r] != -1) return memo[l][r];
        int result = compute(l + 1, r) + in[l] * in[l + 1];
        for (int i = l + 1; i < r; i++) {
            result = Math.max(result, compute(l, i - 1) + in[i - 1] * in[i] * in[i + 1] + compute(i + 1, r));
        }
        result = Math.max(result, compute(l, r - 1) + in[r] * in[r - 1]);
        memo[l][r] = result;
        return result;
    }

    public static void main(String[] args) {
        BurstBallons burstBallons = new BurstBallons();
        System.out.println(burstBallons.maxCoins(new int[]{3, 1, 5, 8}));
    }
}
