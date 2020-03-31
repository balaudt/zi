package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class MagicSqauresTest {
	private MagicSqaures solution = new MagicSqaures();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{4, 3, 8, 4}, {9, 5, 1, 9}, {2, 7, 6, 2}}, 1}
		};
	}

	@Test(dataProvider = "data")
	public void testNumMagicSquaresInside(int[][] in, int expected) {
		assertEquals(solution.numMagicSquaresInside(in), expected);
	}
}
