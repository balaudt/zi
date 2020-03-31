package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class SomePermTest {
	private SomePerm somePerm = new SomePerm();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{1, 2, 3}, {5, 4, 0}}, -1}
		};
	}

	@Test(dataProvider = "data")
	public void testSlidingPuzzle(int[][] board, int expected) {
		assertEquals(somePerm.slidingPuzzle(board), expected);
	}
}
