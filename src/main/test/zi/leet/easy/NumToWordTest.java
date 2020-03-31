package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class NumToWordTest {
	private NumToWord solution = new NumToWord();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{1234567891, "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"}
		};
	}

	@Test(dataProvider = "data")
	public void testNumberToWords(int n, String expected) {
		assertEquals(solution.numberToWords(n), expected);
	}
}
