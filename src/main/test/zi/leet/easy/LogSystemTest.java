package zi.leet.easy;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class LogSystemTest {
	@Test
	public void testSample() {
		LogSystem sol = new LogSystem();
		sol.put(1, "2017:01:01:23:59:59");
		sol.put(2, "2017:01:01:22:59:59");
		sol.put(3, "2016:01:01:00:00:00");
		assertEquals(new HashSet<>(sol.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year")), new HashSet<>(Arrays.asList(3, 2, 1)));
		assertEquals(new HashSet<>(sol.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour")), new HashSet<>(Arrays.asList(2, 1)));
	}

}
