package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class WorkerBikeTest {
	private WorkerBike solution = new WorkerBike();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[][]{{0, 0}, {2, 1}}, new int[][]{{1, 2}, {3, 3}}, 6},
				{new int[][]{{0, 0}, {1, 1}, {2, 0}}, new int[][]{{1, 0}, {2, 2}, {2, 1}}, 4}
		};
	}

	@Test(dataProvider = "data")
	public void testAssignBikes(int[][] workers, int[][] bikes, int expected) {
		assertEquals(solution.assignBikes(workers, bikes), expected);
	}
}
