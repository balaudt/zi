package zi.leet.mock.microsoft;

/**
 * @author balamurugan
 */
public class BuyAndSell {
	public int maxProfit(int[] prices) {
		int i = 0, n = prices.length, result = 0;
		while (i + 1 < n && prices[i + 1] <= prices[i]) i++;
		while (i + 1 < n) {
			int st = i;
			while (i + 1 < n && prices[i + 1] >= prices[i]) i++;
			result += prices[i] - prices[st];
			while (i + 1 < n && prices[i + 1] <= prices[i]) i++;
		}
		return result;
	}
}
