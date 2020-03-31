package zi.leet.easy;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class BuyAndSellKTest {
	private BuyAndSellK solution = new BuyAndSellK();

	@DataProvider
	public static Object[][] data() throws IOException {
		return new Object[][]{
				{0, 1000000000, readFile("/Users/balamurugan/Temp/test.in")}
		};
	}

	@Test(dataProvider = "data")
	public void testMaxProfit(int expected, int k, int[] in) {
		assertEquals(solution.maxProfit(k, in), expected);
	}

	private static int[] readFile(String f) throws IOException {
		String[] s = Files.toString(new File(f), Charsets.UTF_8).split(",");
		int[] out = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			out[i] = Integer.parseInt(s[i].trim());
		}
		return out;
	}
}
