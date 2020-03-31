package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class BasicCalcTest {
	private BasicCalc solution = new BasicCalc();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"1 + 1", 2},
				{"(1+(4+5+2)-3)+(6+8)", 23},
				{"2-1+2", 3},
				{"6-8-7", -9},
				{"3+2*2", 7},
				{"1+2*5/3+6/4*2", 6}
		};
	}

	@Test(dataProvider = "data")
	public void testCalculate(String in, int expected) {
		assertEquals(solution.calculate(in), expected);
	}
}
