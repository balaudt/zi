package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class MissingNumbersTest {
	private MissingNumbers solution = new MissingNumbers();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{2147483647}, 0, 2147483647, Collections.singletonList("0->2147483646")}
		};
	}

	@Test(dataProvider = "data")
	public void testFindMissingRanges(int[] nums, int l, int u, List<String> expected) {
		assertEquals(solution.findMissingRanges(nums, l, u), expected);
	}
}
