package zi.leet.easy;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class KeyboardRowTest {
	private KeyboardRow solution = new KeyboardRow();

	@Test
	public void testFindWords() {
		assertEquals(solution.findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"}), new String[]{"Alaska", "Dad"});
	}
}
