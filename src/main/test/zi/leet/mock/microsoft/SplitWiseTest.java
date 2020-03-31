package zi.leet.mock.microsoft;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class SplitWiseTest {
	private SplitWise solution = new SplitWise();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{new int[][]{{1, 2, 3}, {1, 3, 3}, {6, 4, 1}, {5, 4, 4}}, 4},
//				{new int[][]{{0, 1, 10}, {2, 0, 5}}, 2},
//				{new int[][]{{0, 1, 10}, {1, 0, 1}, {1, 2, 5}, {2, 0, 5}}, 1}
//				{new int[][]{{0,6,7},{0,7,7},{1,4,4},{1,5,2},{2,5,3},{3,7,1}}, 6},
				{new int[][]{{1, 8, 1}, {1, 13, 21}, {2, 8, 10}, {3, 9, 20}, {4, 10, 61}, {5, 11, 61}, {6, 12, 59}, {7, 13, 60}}, 8}
		};
	}

	@Test(dataProvider = "data")
	public void testMinTransfers(int[][] transactions, int expected) {
		assertEquals(solution.minTransfers(transactions), expected);
	}
}
