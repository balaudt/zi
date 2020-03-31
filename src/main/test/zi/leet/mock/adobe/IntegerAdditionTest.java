package zi.leet.mock.adobe;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class IntegerAdditionTest {

	@Test
	public void testAddStrings() {
		IntegerAddition solution = new IntegerAddition();
//		assertEquals(solution.addStrings("39481237", "23177"), "39504414");
		assertEquals(solution.addStrings("9", "1"), "10");
	}
}
