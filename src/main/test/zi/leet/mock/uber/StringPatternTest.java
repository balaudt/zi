package zi.leet.mock.uber;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class StringPatternTest {
	private StringPattern solution = new StringPattern();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"abba", "dog cat cat dog", true},
				{"abba", "dog cat cat fish", false},
				{"aaaa", "dog cat cat dog", false},
				{"abba", "dog dog dog dog", false}
		};
	}

	@Test(dataProvider = "data")
	public void testWordPattern(String p, String s, boolean expected) {
		assertEquals(solution.wordPattern(p, s), expected);
	}
}
