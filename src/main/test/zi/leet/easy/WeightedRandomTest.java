package zi.leet.easy;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class WeightedRandomTest {

	@Test
	public void testFail1() {
		WeightedRandom solution = new WeightedRandom(new int[]{3, 14, 1, 7});
		Map<Integer, Integer> cts = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			cts.put(i, 0);
		}
		for (int i = 0; i < 10000; i++) {
			int index = solution.pickIndex();
			Integer ct = cts.get(index);
			cts.put(index, ct + 1);
		}
		System.out.println(cts);
	}
}
