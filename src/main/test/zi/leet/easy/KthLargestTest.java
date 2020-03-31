package zi.leet.easy;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class KthLargestTest {

	@Test
	public void testAdd() {
		KthLargest solution = new KthLargest(3, new int[]{4, 5, 8, 2});
		assertEquals(solution.add(3), 4);
		assertEquals(solution.add(5), 5);
		assertEquals(solution.add(10), 5);
		assertEquals(solution.add(9), 8);
		assertEquals(solution.add(4), 8);
	}
}
