package zi.leet.medium;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class LongDivisionTest {
	private LongDivision solution = new LongDivision();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{2, 3, "0.(6)"},
//				{1, 81, "0.(012345679)"},
//				{7, 12, "0.58(3)"},
//				{1, 2, "0.5"},
//				{5, 1, "5.0"},
//				{-1, -2147483648, "0.0000000004656612873077392578125"},
				{-2147483648, 1, "-2147483648"}
		};
	}

	@Test(dataProvider = "data")
	public void testFractionToDecimal(int n, int d, String expected) {
		assertEquals(solution.fractionToDecimal(n, d), expected);
	}
}
