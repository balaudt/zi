package zi.leet.mock.fb;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class BuyAndSellStock {
	public int maxProfit(int[] prices) {
		int min = prices[0], result = Integer.MIN_VALUE;
		for (int i = 1; i < prices.length; i++) {
			result = Math.max(result, prices[i] - min);
			min = Math.min(min, prices[i]);
		}
		return Math.max(result, 0);
	}
}
