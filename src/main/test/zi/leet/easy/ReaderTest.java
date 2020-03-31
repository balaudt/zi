package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class ReaderTest {
	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"ab", new int[]{1, 2}}
		};
	}

	@Test(dataProvider = "data")
	public void testRead(String in, int[] n) {
		Reader reader = new ReaderTester(in);
		Arrays.stream(n).forEach(i -> {
			char[] buf = new char[i];
			int out = reader.read(buf, i);
			System.out.println(new String(buf) + "\t" + out);
		});
	}
}
