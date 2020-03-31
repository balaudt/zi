package zi.leet.hard;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class LargestRectangleHistogramTest {
	private LargestRectangleHistogram solution = new LargestRectangleHistogram();

	@Test
	public void testLargestRectangleArea() {
		assertEquals(solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}), 10);
	}
}
