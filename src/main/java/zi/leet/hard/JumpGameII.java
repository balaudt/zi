package zi.leet.hard;

import java.util.Arrays;

//https://leetcode.com/problems/jump-game-ii/
public class JumpGameII {
    private int[] in, memo;
    private int n;

    public int jump1(int[] nums) {
        in = nums;
        n = in.length;
        memo = new int[n];
        Arrays.fill(memo, -1);
        return min(0);
    }

    private int min(int st) {
        if (st >= n - 1) return 0;
        if (memo[st] != -1) return memo[st];
        if (in[st] == 0) {
            memo[st] = Integer.MAX_VALUE;
            return memo[st];
        }
        int min = Integer.MAX_VALUE;
        for (int i = st + 1; i <= st + in[st]; i++) {
            min = Math.min(min, min(i));
        }
        memo[st] = min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min + 1;
        return memo[st];
    }

    public int jump(int[] nums) {
        int ind = 0, result = 0;
        int n = nums.length;
        while (ind < n - 1) {
            result++;
            int maxCoverage = Integer.MIN_VALUE, maxInd = -1;
            for (int i = ind + 1; i <= nums[ind] + ind && i < n; i++) {
                if (i == n - 1) return result;
                int coverage = i == n - 1 ? n - 1 : i + nums[i];
                if (coverage >= maxCoverage) {
                    maxInd = i;
                    maxCoverage = coverage;
                }
            }
            ind = maxInd;
        }
        return result;
    }
}
