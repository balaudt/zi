package zi.leet.easy;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class SnakeGameTest {
	@Test
	public void testSample() {
		SnakeGame solution = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});
		assertEquals(solution.move("R"), 0);
		assertEquals(solution.move("D"), 0);
		assertEquals(solution.move("R"), 1);
		assertEquals(solution.move("U"), 1);
		assertEquals(solution.move("L"), 2);
		assertEquals(solution.move("U"), -1);
	}

	@Test
	public void testFail1() {
		SnakeGame solution = new SnakeGame(2, 2, new int[][]{{0, 1}});
		assertEquals(solution.move("R"), 1);
		assertEquals(solution.move("D"), 1);
	}

	@Test
	public void testFail2() {
		SnakeGame solution = new SnakeGame(3, 3, new int[][]{{2, 0}, {0, 2}});
		assertEquals(solution.move("D"), 0);
		assertEquals(solution.move("D"), 1);
		assertEquals(solution.move("R"), 1);
		assertEquals(solution.move("U"), 1);
		assertEquals(solution.move("U"), 1);
		assertEquals(solution.move("R"), 2);
	}

	@Test
	public void testFail3() {
		SnakeGame solution = new SnakeGame(5, 3, new int[][]{{1, 1}, {2, 2}, {1, 3}, {0, 4}, {0, 0}});
		assertEquals(solution.move("D"), 0);
		assertEquals(solution.move("R"), 1);
		assertEquals(solution.move("D"), 1);
		assertEquals(solution.move("R"), 2);
		assertEquals(solution.move("U"), 2);
		assertEquals(solution.move("R"), 3);
		assertEquals(solution.move("U"), 3);
		assertEquals(solution.move("R"), 4);
	}

	@Test
	public void testFail4() {
		SnakeGame solution = new SnakeGame(3, 3, new int[][]{{2, 0}, {0, 0}, {0, 2}, {2, 2}});
		assertEquals(solution.move("D"), 0);
		assertEquals(solution.move("D"), 1);
		assertEquals(solution.move("R"), 1);
		assertEquals(solution.move("U"), 1);
		assertEquals(solution.move("U"), 1);
		assertEquals(solution.move("L"), 2);
		assertEquals(solution.move("D"), 2);
		assertEquals(solution.move("R"), 2);
		assertEquals(solution.move("R"), 2);
		assertEquals(solution.move("U"), 3);
		assertEquals(solution.move("L"), 3);
		assertEquals(solution.move("D"), 3);
	}

	@Test
	public void testFail5() {
		SnakeGame solution = new SnakeGame(3, 3, new int[][]{{2, 0}, {0, 0}, {0, 2}, {0, 1}, {2, 2}, {0, 1}});
		assertEquals(solution.move("D"), 0);
		assertEquals(solution.move("D"), 1);
		assertEquals(solution.move("R"), 1);
		assertEquals(solution.move("U"), 1);
		assertEquals(solution.move("U"), 1);
		assertEquals(solution.move("L"), 2);
		assertEquals(solution.move("D"), 2);
		assertEquals(solution.move("R"), 2);
		assertEquals(solution.move("R"), 2);
		assertEquals(solution.move("U"), 3);
		assertEquals(solution.move("L"), 4);
		assertEquals(solution.move("L"), 4);
		assertEquals(solution.move("D"), 4);
		assertEquals(solution.move("R"), 4);
		assertEquals(solution.move("U"), -1);
	}
}
