package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class OddEvenJumpTest {
	private OddEvenJump solution = new OddEvenJump();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{10, 13, 12, 14, 15}, 2},
				{new int[]{2, 3, 1, 1, 4}, 3},
				{new int[]{5, 1, 3, 4, 2}, 3}
		};
	}

	@Test(dataProvider = "data")
	public void testOddEvenJumps(int[] in, int expected) {
		assertEquals(solution.oddEvenJumps(in), expected);
	}
}
