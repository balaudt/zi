package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class SmallestSubsequenceTest {
	private SmallestSubsequence solution = new SmallestSubsequence();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"leetcode", "letcod"}
		};
	}

	@Test(dataProvider = "data")
	public void testSmallestSubsequence(String in, String expected) {
		assertEquals(solution.smallestSubsequence(in), expected);
	}
}
