package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class MyCalendarTwoTest {
	@Test
	public void testBook() {
		MyCalendarTwo solution = new MyCalendarTwo();
		assertTrue(solution.book(10, 20));
		assertTrue(solution.book(50, 60));
		assertTrue(solution.book(10, 40));
		assertFalse(solution.book(5, 15));
		assertTrue(solution.book(5, 10));
		assertTrue(solution.book(25, 55));
	}
}
