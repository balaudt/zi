package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class SubmatrixSumTest {
	private SubmatrixSum solution = new SubmatrixSum();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0, 4}
		};
	}

	@Test(dataProvider = "data")
	public void testNumSubmatrixSumTarget(int[][] in, int t, int expected) {
		assertEquals(solution.numSubmatrixSumTarget(in, t), expected);
	}
}
