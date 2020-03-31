package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class KnightTest {
	private Knight solution = new Knight();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{5, 5, 10}
		};
	}

	@Test(dataProvider = "data")
	public void testMinKnightMoves(int x, int y, int expected) {
		assertEquals(solution.minKnightMoves(x, y), expected);
	}
}
