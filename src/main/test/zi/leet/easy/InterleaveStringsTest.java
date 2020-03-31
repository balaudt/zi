package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class InterleaveStringsTest {
	private InterleaveStrings solution = new InterleaveStrings();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"aabcc", "dbbca", "aadbbcbcac", true},
				{"aabcc", "dbbca", "aadbbbaccc", false}
		};
	}

	@Test(dataProvider = "data")
	public void testIsInterleave(String s1, String s2, String s3, boolean expected) {
		assertEquals(solution.isInterleave(s1, s2, s3), expected);
	}
}
