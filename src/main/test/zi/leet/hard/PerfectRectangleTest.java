package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class PerfectRectangleTest {
	private PerfectRectangle solution = new PerfectRectangle();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}}, true},
//				{new int[][]{{1, 1, 2, 3}, {1, 3, 2, 4}, {3, 1, 4, 2}, {3, 2, 4, 4}}, false},
//				{new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {1, 3, 2, 4}, {3, 2, 4, 4}}, false},
//				{new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {1, 3, 2, 4}, {2, 2, 4, 4}}, false},
				{new int[][]{{0,0,2,1},{0,1,2,2},{0,2,1,3},{1,0,2,1}}, false}
		};
	}

	@Test(dataProvider = "data")
	public void testIsRectangleCover(int[][] in, boolean expected) {
		assertEquals(solution.isRectangleCover(in), expected);
	}
}
