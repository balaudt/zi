package zi.leet.mock.microsoft;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class RotatedBinarySearchTest {
	private RotatedBinarySearch solution = new RotatedBinarySearch();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{new int[]{4, 5, 6, 7, 0, 1, 2}, 0, 4},
//				{new int[]{4, 5, 6, 7, 0, 1, 2}, 3, -1},
//				{new int[]{0, 1, 2, 3, 4, 5, 6}, 3, 3},
//				{new int[]{0, 1, 2, 3, 4, 5, 6}, 7, -1},
				{new int[]{1, 3}, 1, 0}
		};
	}

	@Test(dataProvider = "data")
	public void testSearch(int[] in, int target, int expected) {
		assertEquals(solution.search(in, target), expected);
	}
}
