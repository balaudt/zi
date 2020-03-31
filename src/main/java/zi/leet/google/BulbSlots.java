package zi.leet.google;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author balamurugan
 */
public class BulbSlots {
	public int kEmptySlots(int[] bulbs, int K) {
		TreeSet<Integer> onBulbs = new TreeSet<>();
		for (int i = 0; i < bulbs.length; i++) {
			int currentBulb = bulbs[i];
			Integer nei = onBulbs.lower(currentBulb);
			if (nei != null && currentBulb - nei == K + 1) return (i + 1);
			nei = onBulbs.higher(currentBulb);
			if (nei != null && nei - currentBulb == K + 1) return (i + 1);
			onBulbs.add(currentBulb);
		}
		return -1;
	}
}
