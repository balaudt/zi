package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TrappingRainWaterTest {
	private TrappingRainWater solution = new TrappingRainWater();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6},
				{new int[]{5, 2, 1, 2, 1, 5}, 14}
		};
	}

	@Test(dataProvider = "data")
	public void testTrap(int[] in, int expected) {
		assertEquals(solution.trap(in), expected);
	}
}
