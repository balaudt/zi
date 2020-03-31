package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class PalindromeTest {
	private Palindrome solution = new Palindrome();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"OP", false}
		};
	}

	@Test(dataProvider = "data")
	public void testIsPalindrome(String in, boolean expected) {
		assertEquals(solution.isPalindrome(in), expected);
	}
}
