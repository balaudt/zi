package zi.leet.medium;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class DecodeStringTest {
	private DecodeString solution = new DecodeString();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"3[a]2[bc]", "aaabcbc"},
				{"3[a2[c]]", "accaccacc"},
				{"2[abc]3[cd]ef", "abcabccdcdcdef"}
		};
	}

	@Test(dataProvider = "data")
	public void testDecodeString(String in, String expected) {
		assertEquals(solution.decodeString(in), expected);
	}
}
