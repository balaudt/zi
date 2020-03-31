package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class MissingElementTest {
	private MissingElement solution = new MissingElement();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{4, 7, 9, 10}, 1, 5},
				{new int[]{4, 7, 9, 10}, 3, 8},
				{new int[]{1, 2, 4}, 3, 6},
				{new int[]{2, 3, 5, 7}, 1, 4}
		};
	}

	@Test(dataProvider = "data")
	public void testMissingElement(int[] nums, int k, int expected) {
		assertEquals(solution.missingElement(nums, k), expected);
	}
}
