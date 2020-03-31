package zi.leet.medium;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author balamurugan
 */
public class TimeMapTest {
	@Test
	public void testSample() {
		TimeMap timeMap = new TimeMap();
		timeMap.set("foo", "bar", 1);
		assertEquals(timeMap.get("foo", 1), "bar");
		assertEquals(timeMap.get("foo", 3), "bar");
		timeMap.set("foo", "bar2", 4);
		assertEquals(timeMap.get("foo", 4), "bar2");
		assertEquals(timeMap.get("foo", 5), "bar2");
	}


	@Test
	public void testWA1(){
		TimeMap timeMap = new TimeMap();
		timeMap.set("love", "high", 10);
		timeMap.set("love", "low", 20);
		assertEquals(timeMap.get("love", 5), "");
		assertEquals(timeMap.get("love", 10), "high");
		assertEquals(timeMap.get("love", 15), "high");
		assertEquals(timeMap.get("love", 20), "low");
		assertEquals(timeMap.get("love", 25), "low");
	}
}
