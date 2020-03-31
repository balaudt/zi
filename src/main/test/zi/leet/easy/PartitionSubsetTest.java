package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class PartitionSubsetTest {
	private PartitionSubset solution = new PartitionSubset();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{new int[]{4, 3, 2, 3, 5, 2, 1}, 4, true},
				{new int[]{10, 10, 10, 7, 7, 7, 7, 7, 7, 6, 6, 6}, 3, true}
		};
	}

	@Test(dataProvider = "data")
	public void testCanPartitionKSubsets(int[] in, int k, boolean expected) {
		assertEquals(solution.canPartitionKSubsets(in, k), expected);
	}
}
