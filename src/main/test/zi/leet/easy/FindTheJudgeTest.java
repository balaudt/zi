package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class FindTheJudgeTest {
	private FindTheJudge solution = new FindTheJudge();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{2, new int[][]{{1, 2}}, 2},
				{3, new int[][]{{1, 3}, {2, 3}}, 3},
				{3, new int[][]{{1, 3}, {2, 3}, {3, 1}}, -1},
				{3, new int[][]{{1, 2}, {2, 3}}, -1},
				{4, new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}}, 3}
		};
	}

	@Test(dataProvider = "data")
	public void testFindJudge(int n, int[][] trust, int expected) {
		assertEquals(solution.findJudge(n, trust), expected);
	}
}
