package zi.leet.mock.microsoft;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class BuyAndSellTestN {
	private BuyAndSell solution = new BuyAndSell();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{7, 1, 5, 3, 6, 4}, 7},
				{new int[]{1, 2, 3, 4, 5}, 4},
				{new int[]{7, 6, 4, 3, 1}, 0},
				{new int[]{1, 3}, 2}
		};
	}

	@Test(dataProvider = "data")
	public void testMaxProfit(int[] prices, int expected) {
		assertEquals(solution.maxProfit(prices), expected);
	}
}
