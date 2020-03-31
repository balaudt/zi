package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class SomeGridTest {
	private SomeGrid solution = new SomeGrid();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}, 7}
		};
	}

	@Test(dataProvider = "data")
	public void testShortestDistance(int[][] grid, int expected) {
		assertEquals(solution.shortestDistance(grid), expected);
	}
}
