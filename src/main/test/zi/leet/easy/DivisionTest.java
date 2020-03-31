package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class DivisionTest {
	private Division solution = new Division();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{10, 3, 3}
		};
	}

	@Test(dataProvider = "data")
	public void testDivide(int dividend, int divisor, int quotient) {
		assertEquals(solution.divide(dividend, divisor), quotient);
	}
}
