package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class DiagonalMatrixTest {
	private DiagonalMatrix solution = new DiagonalMatrix();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}}, 3}
		};
	}

	@Test(dataProvider = "data")
	public void testLongestLine(int[][] in, int expected) {
		assertEquals(solution.longestLine(in), expected);
	}
}
