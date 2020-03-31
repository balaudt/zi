package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class SerDeTest {
	private SerDe solution  = new SerDe();
	@DataProvider
	public static Object[][] data() {
		return new Object[][]{

		};
	}

	@Test(dataProvider = "data")
	public void testCases(List<String> in){
		assertEquals(solution.decode(solution.encode(in)), in);
	}

	@Test
	public void testEncode() {
		SerDe serDe = new SerDe();
		List<String> in = Arrays.asList("test", "apple", "mock", "");
		String encodedStr = serDe.encode(in);
		assertEquals(serDe.decode(encodedStr), in);
	}
}
