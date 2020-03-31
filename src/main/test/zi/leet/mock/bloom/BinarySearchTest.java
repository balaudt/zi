package zi.leet.mock.bloom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class BinarySearchTest {
	private BinarySearch solution = new BinarySearch();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{1, 3, 5, 6}, 5, 2},
				{new int[]{1, 3, 5, 6}, 2, 1},
				{new int[]{1, 3, 5, 6}, 7, 4},
				{new int[]{1, 3, 5, 6}, 0, 0},
		};
	}

	@Test(dataProvider = "data")
	public void testSearchInsert(int[] in, int target, int expected) {
		assertEquals(solution.searchInsert(in, target), expected);
	}
}
