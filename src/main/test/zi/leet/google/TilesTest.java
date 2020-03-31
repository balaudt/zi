package zi.leet.google;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class TilesTest {
	private Tiles tiles = new Tiles();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"AAB", 8},
				{"AAABBC", 188}
		};
	}

	@Test(dataProvider = "data")
	public void testNumTilePossibilities(String in, int expected) {
		assertEquals(tiles.numTilePossibilities(in), expected);
	}
}
