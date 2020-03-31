package zi.leet.mock.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/number-of-equivalent-domino-pairs/
public class DominoPairs {
	public int numEquivDominoPairs(int[][] dominoes) {
		Map<Integer, Integer> dominoCounter = new HashMap<>();
		for (int i = 0; i < dominoes.length; i++) {
			int[] domino = dominoes[i];
			int x = Math.min(domino[0], domino[1]), y = Math.max(domino[0], domino[1]);
			int result = x * 10 + y;
			Integer count = dominoCounter.getOrDefault(result, 0);
			dominoCounter.put(result, count + 1);
		}
		int result = 0;
		for (Integer count : dominoCounter.values()) {
			if (count > 1) result += count * (count - 1) / 2;
		}
		return result;
	}
}
