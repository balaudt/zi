package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class ReplaceStringTest {
	private ReplaceString solution = new ReplaceString();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"jjievdtjfb", new int[]{4, 6, 1}, new String[]{"md", "tjgb", "jf"}, new String[]{"foe", "oov", "e"}, "jjievdtjfb"}
		};
	}

	@Test(dataProvider = "data")
	public void testFindReplaceString(String s, int[] indices, String[] src, String[] tar, String expected) {
		assertEquals(solution.findReplaceString(s, indices, src, tar), expected);
	}
}
