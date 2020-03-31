package zi.leet.mock.apple;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author balamurugan
 */
public class CandidateSumsTest {
	private CandidateSums solution = new CandidateSums();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{2, 3, 6, 7}, 7},
//				{new int[]{2, 3, 5}, 8},
		};
	}

	@Test(dataProvider = "data")
	public void testCombinationSum(int[] candidates, int target) {
		solution.combinationSum(candidates, target).forEach(System.out::println);
	}
}
