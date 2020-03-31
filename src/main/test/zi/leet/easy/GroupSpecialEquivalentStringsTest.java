package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class GroupSpecialEquivalentStringsTest {
	private GroupSpecialEquivalentStrings solution = new GroupSpecialEquivalentStrings();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new String[]{"a", "b", "c", "a", "c", "c"}, 3},
				{new String[]{"aa", "bb", "ab", "ba"}, 4},
				{new String[]{"abc", "acb", "bac", "bca", "cab", "cba"}, 3},
				{new String[]{"abcd", "cdab", "adcb", "cbad"}, 1}
		};
	}

	@Test(dataProvider = "data")
	public void testNumSpecialEquivGroups(String[] in, int expected) {
		assertEquals(solution.numSpecialEquivGroups(in), expected);
	}
}
