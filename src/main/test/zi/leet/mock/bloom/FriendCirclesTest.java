package zi.leet.mock.bloom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class FriendCirclesTest {
	private FriendCircles solution = new FriendCircles();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}, 2},
//				{new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}, 1}
		};
	}

	@Test(dataProvider = "data")
	public void testFindCircleNum(int[][] in, int expected) {
		assertEquals(solution.findCircleNum(in), expected);
	}
}
