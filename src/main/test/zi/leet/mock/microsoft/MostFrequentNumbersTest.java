package zi.leet.mock.microsoft;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class MostFrequentNumbersTest {
	private MostFrequentNumbers solution = new MostFrequentNumbers();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{1, 1, 1, 3, 3, 2, 2, 2}, Arrays.asList(1, 2)}
		};
	}

	@Test(dataProvider = "data")
	public void testMajorityElement(int[] in, List<Integer> expected) {
		assertEquals(solution.majorityElement(in), expected);
	}
}
