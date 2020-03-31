package zi.leet.easy;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/two-city-scheduling
public class TwoCitySchedule {
    Map<int[], Integer> memo = new HashMap<>();
    int[][] costs;
    int n;

    public int twoCitySchedCost(int[][] costs) {
        this.costs = costs;
        n = costs.length / 2;
        return minCost(new int[]{0, 0, 0});
    }

    private int minCost(int[] in) {
        int aNum = in[0], bNum = in[1], ind = in[2];
        if (ind == costs.length) {
            return 0;
        }
        if (memo.containsKey(in)) {
            return memo.get(in);
        }
        int minCost;
        if (aNum == n) {
            minCost = minCost(new int[]{aNum, bNum + 1, ind + 1}) + costs[ind][1];
        } else if (bNum == n) {
            minCost = minCost(new int[]{aNum + 1, bNum, ind + 1}) + costs[ind][0];
        } else {
            minCost = Math.min(minCost(new int[]{aNum + 1, bNum, ind + 1}) + costs[ind][0],
                    minCost(new int[]{aNum, bNum + 1, ind + 1}) + costs[ind][1]);
        }
        memo.put(in, minCost);
        return minCost;
    }

    public static void main(String[] args) {
        TwoCitySchedule twoCitySchedule = new TwoCitySchedule();
        System.out.println(twoCitySchedule.twoCitySchedCost(new int[][]{{10, 20}, {30, 200}, {400, 50}, {30, 20}}));
    }
}
