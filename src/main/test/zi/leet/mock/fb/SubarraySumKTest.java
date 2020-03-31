package zi.leet.mock.fb;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class SubarraySumKTest {
	private SubarraySumK solution = new SubarraySumK();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{new int[]{-2, -1, 2, 1}, 1, 2},
//				{new int[]{1, -1, 5, -2, 3}, 3, 4},
				{new int[]{-1}, -1, 1}
		};
	}

	@Test(dataProvider = "data")
	public void testMaxSubArrayLen(int[] in, int k, int expected) {
		assertEquals(solution.maxSubArrayLen(in, k), expected);
	}
}
