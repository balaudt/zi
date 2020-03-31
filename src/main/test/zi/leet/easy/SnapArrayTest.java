package zi.leet.easy;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class SnapArrayTest {
	@Test
	public void sampleTest(){
		SnapArray sol = new SnapArray(3);
		sol.set(0, 5);
		sol.snap();
		sol.set(0, 6);
		assertEquals(sol.get(0, 0), 5);
	}

}
