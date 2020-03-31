package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class CpuCyclesTest {
	private CpuCycles solution = new CpuCycles();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new String[]{"A", "A", "A", "B", "B", "B"}, 2, 8}
		};
	}

	@Test(dataProvider = "data")
	public void testLeastInterval(String[] in, int n, int expected) {
		char[] tasks = new char[in.length];
		for (int i = 0; i < in.length; i++) {
			tasks[i] = in[i].charAt(0);
		}
		assertEquals(solution.leastInterval(tasks, n), expected);
	}
}
