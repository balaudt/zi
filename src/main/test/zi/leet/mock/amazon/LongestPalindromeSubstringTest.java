package zi.leet.mock.amazon;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class LongestPalindromeSubstringTest {
	private LongestPalindromeSubstring solution = new LongestPalindromeSubstring();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{"cbbd", "bb"},
				{"abcda", "a"}
		};
	}

	@Test(dataProvider = "data")
	public void testLongestPalindrome(String in, String expected) {
		assertEquals(solution.longestPalindrome(in), expected);
	}
}
