package zi.leet.google;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class BulbSlotsTest {
	private BulbSlots solution = new BulbSlots();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{1, 3, 2}, 1, 2},
				{new int[]{1, 2, 3}, 1, -1}
		};
	}

	@Test(dataProvider = "data")
	public void testKEmptySlots(int[] bulbs, int k, int expected) {
		assertEquals(solution.kEmptySlots(bulbs, k), expected);
	}
}
