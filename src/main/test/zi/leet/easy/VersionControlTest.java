package zi.leet.easy;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class VersionControlTest {

	@Test
	public void testIsBadVersion() {
		BadVersionFinder sol = new BadVersionFinder();
		assertEquals(sol.firstBadVersion(2126753390), 1702766719);
	}
}
