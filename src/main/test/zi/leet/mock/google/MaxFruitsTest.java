package zi.leet.mock.google;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class MaxFruitsTest {
	private MaxFruits solution = new MaxFruits();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{1, 2, 1}, 3},
				{new int[]{0, 1, 2, 2}, 3},
				{new int[]{1, 2, 3, 2, 2}, 4},
				{new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}, 5},
				{new int[]{0, 1, 6, 6, 4, 4, 6}, 5}
		};
	}

	@Test(dataProvider = "data")
	public void testTotalFruit(int[] in, int expected) {
		assertEquals(solution.totalFruit(in), expected);
	}
}
