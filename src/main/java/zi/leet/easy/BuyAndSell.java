package zi.leet.easy;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class BuyAndSell {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int profit = 0, min = prices[0], max = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min || prices[i] < prices[i - 1]) {
                profit += max - min;
                min = prices[i];
                max = prices[i];
            } else if (prices[i] > max) {
                max = prices[i];
            }
        }
        profit += max - min;
        return profit;
    }

}
