package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

/**
 * @author balamurugan
 */
public class IndexPairsTest {
	private IndexPairs solution = new IndexPairs();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"thestoryofleetcodeandme", new String[]{"story", "fleet", "leetcode"}, new int[][]{{3, 7}, {9, 13}, {10, 17}}},
				{"ababa", new String[]{"aba", "ab"}, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}}}
		};
	}

	@Test(dataProvider = "data")
	public void testIndexPairs(String text, String[] words, int[][] expected) {
		assertTrue(Arrays.deepEquals(solution.indexPairs(text, words), expected));
	}
}
