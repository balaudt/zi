package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class StringDecoderTest {
	private StringDecoder solution = new StringDecoder();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"3[a2[c]]", "accaccacc"}
		};
	}

	@Test(dataProvider = "data")
	public void testDecodeString(String in, String out) {
		assertEquals(solution.decodeString(in), out);
	}
}
