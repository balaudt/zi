package zi.leet.mock.amazon;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class MovingAverageTest {
	@Test
	public void testOperations() {
		MovingAverage solution = new MovingAverage(3);
		assertEquals(solution.next(1), 1.0);
		assertEquals(solution.next(10), 11 / 2.0);
		assertEquals(solution.next(3), 14 / 3.0);
		assertEquals(solution.next(5), 6.0);
	}

}
