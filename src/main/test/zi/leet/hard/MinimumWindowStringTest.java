package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class MinimumWindowStringTest {
	private MinimumWindowString solution = new MinimumWindowString();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"ADOBECODEBANC", "ABC", "BANC"},
				{"a", "aa", ""},
				{"aa", "aa", "aa"},
				{"aaaaaabbbcd", "abcd", "abbbcd"}
		};
	}

	@Test(dataProvider = "data")
	public void testMinWindow(String s, String t, String expected) {
		assertEquals(solution.minWindow(s, t), expected);
	}
}
