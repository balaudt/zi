package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class JewelStonesTest {
	private JewelStones solution = new JewelStones();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"aA", "aAAbbbb", 3},
				{"z", "ZZ", 0}
		};
	}

	@Test(dataProvider = "data")
	public void testNumJewelsInStones(String j, String s, int expected) {
		assertEquals(solution.numJewelsInStones(j, s), expected);
	}
}
