package zi.leet.mock.google;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class UniqueEmailsTest {
	private UniqueEmails solution = new UniqueEmails();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"}, 2},
				{new String[]{"test.email+alex@leetcode.com","test.email.leet+alex@code.com"}, 2}
		};
	}

	@Test(dataProvider = "data")
	public void testNumUniqueEmails(String[] emails, int expected) {
		assertEquals(solution.numUniqueEmails(emails), expected);
	}
}
