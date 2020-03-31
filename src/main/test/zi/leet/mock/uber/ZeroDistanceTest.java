package zi.leet.mock.uber;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

/**
 * @author balamurugan
 */
public class ZeroDistanceTest {
	private ZeroDistance solution = new ZeroDistance();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},
						new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}},
				{new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}},
						new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 2, 1}}}
		};
	}

	@Test(dataProvider = "data")
	public void testUpdateMatrix(int[][] in, int[][] expected) {
		assertTrue(Arrays.deepEquals(solution.updateMatrix(in), expected));
	}
}
