package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class PathMinMaxTest {
	private PathMinMax solution = new PathMinMax();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{new int[][]{{5, 4, 5}, {1, 2, 6}, {7, 4, 6}}, 4},
				{new int[][]{{3, 4, 6, 3, 4}, {0, 2, 1, 1, 7}, {8, 8, 3, 2, 7}, {3, 2, 4, 9, 8}, {4, 1, 2, 0, 0}, {4, 6, 5, 4, 3}}, 3}
		};
	}

	@Test(dataProvider = "data")
	public void testMaximumMinimumPath(int[][] in, int expected) {
		assertEquals(solution.maximumMinimumPath(in), expected);
	}
}
