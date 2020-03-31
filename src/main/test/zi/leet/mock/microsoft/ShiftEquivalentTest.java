package zi.leet.mock.microsoft;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class ShiftEquivalentTest {
	private ShiftEquivalent solution = new ShiftEquivalent();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"abcde", "cdeab", true},
				{"abcde", "abced", false}
		};
	}

	@Test(dataProvider = "data")
	public void testRotateString(String a, String b, boolean expected) {
		assertEquals(solution.rotateString(a, b), expected);
	}
}
