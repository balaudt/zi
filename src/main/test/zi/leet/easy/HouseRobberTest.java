package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class HouseRobberTest {
	private HouseRobber solution = new HouseRobber();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{1, 2, 3, 1}, 4},
				{new int[]{2, 7, 9, 3, 1}, 12}
		};
	}

	@Test(dataProvider = "data")
	public void testRob(int[] in, int expected) {
		assertEquals(solution.rob(in), expected);
	}
}
