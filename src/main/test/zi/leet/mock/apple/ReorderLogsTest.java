package zi.leet.mock.apple;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class ReorderLogsTest {
	private ReorderLogs solution = new ReorderLogs();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"},
						new String[]{"g1 act car", "a8 act zoo", "ab1 off key dog", "a1 9 2 3 1", "zo4 4 7"}}
		};
	}

	@Test(dataProvider = "data")
	public void testReorderLogFiles(String[] in, String[] expected) {
		assertEquals(solution.reorderLogFiles(in), expected);
	}
}
