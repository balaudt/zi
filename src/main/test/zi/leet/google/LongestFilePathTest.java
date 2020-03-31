package zi.leet.google;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class LongestFilePathTest {
	private LongestFilePath solution = new LongestFilePath();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext", 32}
		};
	}

	@Test(dataProvider = "data")
	public void testLengthLongestPath(String in, int expected) {
		assertEquals(solution.lengthLongestPath(in), expected);
	}
}
