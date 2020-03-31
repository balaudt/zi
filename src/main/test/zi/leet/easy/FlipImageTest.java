package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

/**
 * @author balamurugan
 */
public class FlipImageTest {
	private FlipImage solution = new FlipImage();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 0, 0}}, new int[][]{{1, 0, 0}, {0, 1, 0}, {1, 1, 1}}},
				{new int[][]{{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}}, new int[][]{{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}, {1, 0, 1, 0}}}
		};
	}

	@Test(dataProvider = "data")
	public void testFlipAndInvertImage(int[][] in, int[][] expected) {
		assertTrue(Arrays.deepEquals(solution.flipAndInvertImage(in), expected));
	}
}
