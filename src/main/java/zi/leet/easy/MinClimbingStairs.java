package zi.leet.easy;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/min-cost-climbing-stairs
public class MinClimbingStairs {
    private Map<Integer, Integer> memo;
    private int[] cost;

    public int minCostClimbingStairs(int[] cost) {
        memo = new HashMap<>();
        this.cost = cost;
        return Math.min(minCost(0), minCost(1));
    }

    private int minCost(int s) {
        if (s >= cost.length)
            return 0;
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        int cost = this.cost[s] + Math.min(minCost(s + 1), minCost(s + 2));
        memo.put(s, cost);
        return cost;
    }

    public static void main(String[] args) {
        MinClimbingStairs stairs = new MinClimbingStairs();
        System.out.println(stairs.minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(stairs.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
