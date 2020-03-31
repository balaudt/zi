package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class SpiralOrderTest {
	private SpiralOrder solution = new SpiralOrder();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{1}}, Arrays.asList(1)}
		};
	}

	@Test(dataProvider = "data")
	public void testSpiralOrder(int[][] grid, List<Integer> expected) {
		assertEquals(solution.spiralOrder(grid), expected);
	}
}
