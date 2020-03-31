package zi.leet.mock.microsoft;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class AlienOrderTest {
	private AlienOrder solution = new AlienOrder();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz", false},
				{new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz", false},
				{new String[]{"apple", "apple"}, "abcdefghijklmnopqrstuvwxyz", false}
		};
	}

	@Test(dataProvider = "data")
	public void testIsAlienSorted(String[] words, String order, boolean expected) {
		assertEquals(solution.isAlienSorted(words, order), expected);
	}
}
