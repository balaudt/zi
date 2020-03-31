package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class ExpressionEvaluationTest {
	private ExpressionEvaluation solution = new ExpressionEvaluation();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{"3+2*2", 7},
//				{" 3/2 ", 1},
//				{" 3+5 / 2 ", 5},
				{"100000000/1/2/3/4/5/6/7/8/9/10", 1}
		};
	}

	@Test(dataProvider = "data")
	public void testCalculate(String exp, int expected) {
		assertEquals(solution.calculate(exp), expected);
	}
}
