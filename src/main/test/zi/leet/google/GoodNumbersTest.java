package zi.leet.google;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class GoodNumbersTest {
	private GoodNumbers solution = new GoodNumbers();

	@Test
	public void testRotatedDigits() {
		assertEquals(solution.rotatedDigits(10), 4);
	}
}
