package zi.leet.mock.amazon;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

/**
 * @author balamurugan
 */
public class PrisonAfterNDaysTest {
	private PrisonAfterNDays solution = new PrisonAfterNDays();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7, new int[]{0, 0, 1, 1, 0, 0, 0, 0}}
				{new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000, new int[]{0, 0, 1, 1, 1, 1, 1, 0}}
		};
	}

	@Test(dataProvider = "data")
	public void testPrisonAfterNDays(int[] state, int n, int[] expected) {
		assertTrue(Arrays.equals(solution.prisonAfterNDays(state, n), expected));
	}
}
