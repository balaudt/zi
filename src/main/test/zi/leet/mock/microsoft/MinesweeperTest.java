package zi.leet.mock.microsoft;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

/**
 * @author balamurugan
 */
public class MinesweeperTest {
	private Minesweeper solution = new Minesweeper();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new char[][]{{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}},
						new int[]{3, 0},
						new char[][]{{'B', '1', 'E', '1', 'B'}, {'B', '1', 'M', '1', 'B'}, {'B', '1', '1', '1', 'B'}, {'B', 'B', 'B', 'B', 'B'}}}
		};
	}

	@Test(dataProvider = "data")
	public void testUpdateBoard(char[][] in, int[] click, char[][] expected) {
		char[][] actual = solution.updateBoard(in, click);
		System.out.println(Arrays.deepToString(actual));
		assertTrue(Arrays.deepEquals(actual, expected));
	}
}
