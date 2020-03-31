package zi.leet.mock.adobe;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class ReverseDigitsTest {
	private ReverseDigits solution = new ReverseDigits();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{123, 321},
				{-123, -321},
				{120, 21},
				{2147483647, 0}
		};
	}

	@Test(dataProvider = "data")
	public void testReverse(int in, int expected) {
		assertEquals(solution.reverse(in), expected);
	}
}
