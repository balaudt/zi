package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class SnakeAndLadderTest {
	private SnakeAndLadder solution = new SnakeAndLadder();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{-1, 7, -1}, {-1, 6, 9}, {-1, -1, 2}}, 1},
				{new int[][]{{-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 35, -1, -1, 13, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 15, -1, -1, -1, -1}}, 4},
				{new int[][]{{-1, 4, -1}, {6, 2, 6}, {-1, 3, -1}}, 2}
		};
	}

	@Test(dataProvider = "data")
	public void testSnakesAndLadders(int[][] board, int expected) {
		assertEquals(solution.snakesAndLadders(board), expected);
	}
}
