package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class PalindromeWithEditTest {
	private PalindromeWithEdit solution = new PalindromeWithEdit();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"abcab", false}
		};
	}

	@Test(dataProvider = "data")
	public void testValidPalindrome(String in, boolean expected) {
		assertEquals(solution.validPalindrome(in), expected);
	}
}
