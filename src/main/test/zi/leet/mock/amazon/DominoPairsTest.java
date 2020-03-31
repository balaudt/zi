package zi.leet.mock.amazon;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class DominoPairsTest {
	private DominoPairs solution = new DominoPairs();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{1, 2}, {2, 1}, {3, 4}, {5, 6}}, 1}
		};
	}

	@Test(dataProvider = "data")
	public void testNumEquivDominoPairs(int[][] dominos, int expected) {
		assertEquals(solution.numEquivDominoPairs(dominos), expected);
	}
}
