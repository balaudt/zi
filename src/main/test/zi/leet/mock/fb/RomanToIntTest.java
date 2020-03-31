package zi.leet.mock.fb;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class RomanToIntTest {
	private RomanToInt solution = new RomanToInt();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"III", 3},
				{"IV", 4},
				{"IX", 9},
				{"LVIII", 58},
				{"MCMXCIV", 1994}
		};
	}

	@Test(dataProvider = "data")
	public void testRomanToInt(String roman, int numeral) {
		assertEquals(solution.romanToInt(roman), numeral);
	}
}
