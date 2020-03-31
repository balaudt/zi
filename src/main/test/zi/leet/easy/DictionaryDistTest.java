package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class DictionaryDistTest {
	private DictionaryDist solution = new DictionaryDist();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"), 5}
		};
	}

	@Test(dataProvider = "data")
	public void testLadderLength(String begin, String end, List<String> wordList, int expected) {
		assertEquals(solution.ladderLength(begin, end, wordList), expected);
	}
}
