package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class TopologySortTest {
	private TopologySort ts = new TopologySort();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{2, new int[][]{{1, 0}}},
				{4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}}
		};
	}

	@Test(dataProvider = "data")
	public void testFindOrder(int n, int[][] p) {
		System.out.println(Arrays.toString(ts.findOrder(n, p)));
	}
}
