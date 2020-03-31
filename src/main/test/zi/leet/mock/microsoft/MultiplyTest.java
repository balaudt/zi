package zi.leet.mock.microsoft;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class MultiplyTest {
	private Multiply solution = new Multiply();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"2", "3", "6"},
				{"123", "456", "56088"},
				{"9133", "0", "0"}
		};
	}

	@Test(dataProvider = "data")
	public void testMultiply(String a, String b, String expected) {
		assertEquals(solution.multiply(a, b), expected);
	}
}
