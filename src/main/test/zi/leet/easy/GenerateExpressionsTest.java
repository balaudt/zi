package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class GenerateExpressionsTest {
	private GenerateExpressions solution = new GenerateExpressions();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{"123", 6, Arrays.asList("1+2+3", "1*2*3")},
				{"232", 8, Arrays.asList("2*3+2", "2+3*2")}
		};
	}

	@Test(dataProvider = "data")
	public void testAddOperators(String in, int target, List<String> expected) {
		assertEquals(new HashSet<>(solution.addOperators(in, target)), new HashSet<>(expected));
	}
}
