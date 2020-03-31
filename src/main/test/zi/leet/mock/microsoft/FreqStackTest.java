package zi.leet.mock.microsoft;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class FreqStackTest {
	@Test
	public void test() {
		FreqStack s = new FreqStack();
		s.push(4);
		s.push(0);
		s.push(9);
		s.push(3);
		s.push(4);
		s.push(2);
		assertEquals(s.pop(), 4);
		s.push(6);
		assertEquals(s.pop(), 6);
		s.push(1);
		assertEquals(s.pop(), 1);
		s.push(1);
		assertEquals(s.pop(), 1);
		s.push(4);
		assertEquals(s.pop(), 4);
		assertEquals(s.pop(), 2);
		assertEquals(s.pop(), 3);
		assertEquals(s.pop(), 9);
		assertEquals(s.pop(), 0);
		assertEquals(s.pop(), 4);
	}

}
