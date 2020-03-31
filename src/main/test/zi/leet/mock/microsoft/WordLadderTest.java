package zi.leet.mock.microsoft;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class WordLadderTest {
	private WordLadder solution = new WordLadder();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new char[][]{{'o', 'a', 'a', 'n'},
						{'e', 't', 'a', 'e'},
						{'i', 'h', 'k', 'r'},
						{'i', 'f', 'l', 'v'}
				}, new String[]{"oath", "pea", "eat", "rain"}, new HashSet<>(Arrays.asList("eat", "oath"))},
				{new char[][]{{'a'}, {'a'}}, new String[]{"aaa"}, Collections.emptySet()}
		};
	}

	@Test(dataProvider = "data")
	public void testFindWords(char[][] board, String[] words, Set<String> expected) {
		assertEquals(new HashSet<>(solution.findWords(board, words)), expected);
	}
}
