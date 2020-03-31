package zi.leet.google;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author balamurugan
 */
public class AnagramMapping {
	public int[] anagramMappings(int[] A, int[] B) {
		Map<Integer, Set<Integer>> bPositions = new HashMap<>();
		for (int i = 0; i < B.length; i++) {
			Set<Integer> pos = bPositions.get(B[i]);
			if (pos == null) {
				bPositions.put(B[i], new HashSet<>(Collections.singletonList(i)));
			} else {
				pos.add(i);
			}
		}
		int p[] = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			Set<Integer> pos = bPositions.get(A[i]);
			p[i] = pos.iterator().next();
			pos.remove(p[i]);
		}
		return p;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new AnagramMapping().anagramMappings(new int[]{12, 28, 46, 32, 50}, new int[]{50, 12, 32, 46, 28})));
	}
}
