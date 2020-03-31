package zi.leet.w93;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author balamurugan
 */
public class AdvantageShuffle {
	public int[] advantageCount(int[] A, int[] B) {
		int n = A.length;
		int[] out = new int[n];
		TreeSet<Integer> sortedA = new TreeSet<>();
		Map<Integer, Integer> frequency = new HashMap<>(n);
		for (int i : A) {
			sortedA.add(i);
			if (frequency.containsKey(i)) {
				frequency.put(i, frequency.get(i) + 1);
			} else {
				frequency.put(i, 1);
			}
		}
		List<Integer> unfilledIndices = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Integer higher = sortedA.higher(B[i]);
			if (higher == null) {
				unfilledIndices.add(i);
			} else {
				out[i] = higher;
				Integer numFrequency = frequency.get(higher);
				if (numFrequency == 1) {
					sortedA.remove(higher);
				} else {
					frequency.put(higher, numFrequency - 1);
				}
			}
		}
		int ind = 0;
		for (Integer nextNum : sortedA) {
			Integer numFreq = frequency.get(nextNum);
			for (int i = 0; i < numFreq; i++) {
				out[unfilledIndices.get(ind++)] = nextNum;
			}
		}
		return out;
	}

	public static void main(String[] args) {
		AdvantageShuffle advantageShuffle = new AdvantageShuffle();
		System.out.println(Arrays.toString(advantageShuffle.advantageCount(new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11})));
		System.out.println(Arrays.toString(advantageShuffle.advantageCount(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11})));
		System.out.println(Arrays.toString(advantageShuffle.advantageCount(new int[]{2, 0, 4, 1, 2}, new int[]{1, 3, 0, 0, 2})));
		System.out.println(Arrays.toString(advantageShuffle.advantageCount(new int[]{15, 15, 4, 5, 0, 1, 7, 10, 3, 1, 10, 10, 8, 2, 3}, new int[]{4, 13, 14, 0, 14, 14, 12, 3, 15, 12, 2, 0, 6, 9, 0})));
	}
}
