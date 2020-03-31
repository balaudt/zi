package zi.leet.google;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class LicenseKeyFormatTest {
	private LicenseKeyFormat solution = new LicenseKeyFormat();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{"5F3Z-2e-9-w", 4, "5F3Z-2E9W"},
				{"2-5g-3-J", 2, "2-5G-3J"}
		}
		;
	}

	@Test(dataProvider = "data")
	public void testLicenseKeyFormatting(String s, int k, String expected) {
		assertEquals(solution.licenseKeyFormatting(s, k), expected);
	}
}
