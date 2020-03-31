package zi.leet.mock.apple;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class GenerateParenthesisTest {
	private GenerateParenthesis solution = new GenerateParenthesis();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{3, new HashSet<>(Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"))}
		};
	}

	@Test(dataProvider = "data")
	public void testGenerateParenthesis(int n, Set<String> expected) {
		assertEquals(new HashSet<>(solution.generateParenthesis(n)), expected);
	}
}
