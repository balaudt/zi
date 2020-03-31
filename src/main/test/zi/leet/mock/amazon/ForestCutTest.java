package zi.leet.mock.amazon;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class ForestCutTest {
	private ForestCut solution = new ForestCut();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(0, 0, 4), Arrays.asList(7, 6, 5)), 6}
		};
	}

	@Test(dataProvider = "data")
	public void testCutOffTree(List<List<Integer>> in, int expected) {
		assertEquals(solution.cutOffTree(in), expected);
	}
}
