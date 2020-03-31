package zi.leet.mock.microsoft;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class RemoveKDigitsTest {
	private RemoveKDigits solution = new RemoveKDigits();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"1432219", 3, "1219"},
				{"10200", 1, "200"},
				{"10", 2, "0"}
		};
	}

	@Test(dataProvider = "data")
	public void testRemoveKdigits(String num, int k, String expected) {
		assertEquals(solution.removeKdigits(num, k), expected);
	}
}
