package zi.leet.hard;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class BulbSwitchTest {

	@Test
	public void testBulbSwitch() {
		BulbSwitch bulbSwitch = new BulbSwitch();
		assertEquals(bulbSwitch.bulbSwitch(3), 1);
		assertEquals(bulbSwitch.bulbSwitch(7), 2);
		assertEquals(bulbSwitch.bulbSwitch(8), 2);
		int last = 1;
		for (int i = 1; i < 50; i++) {
			int x = bulbSwitch.bulbSwitch(i);
			if (x != last) {
				System.out.println(i + "\t" + x);
				last = x;
			}
		}
	}
}
