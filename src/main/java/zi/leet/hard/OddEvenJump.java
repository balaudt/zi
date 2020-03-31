package zi.leet.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/odd-even-jump/
public class OddEvenJump {
	class MemoKey {
		int ind;
		boolean isOdd;

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			MemoKey memoKey = (MemoKey) o;
			return ind == memoKey.ind &&
					isOdd == memoKey.isOdd;
		}

		@Override
		public int hashCode() {
			return Objects.hash(ind, isOdd);
		}

		public MemoKey(int ind, boolean isOdd) {
			this.ind = ind;
			this.isOdd = isOdd;
		}
	}

	private int[] in, odd, even;
	private int n;
	private Map<MemoKey, Boolean> memo;

	public int oddEvenJumps(int[] A) {
		n = A.length;
		in = A;
		odd = new int[n];
		even = new int[n];
		memo = new HashMap<>();
		Arrays.fill(odd, -1);
		Arrays.fill(even, -1);
		TreeSet<Integer> set = new TreeSet<>();
		Map<Integer, Integer> indices = new HashMap<>();
		for (int i = n - 1; i >= 0; i--) {
			if (set.contains(in[i])) {
				odd[i] = indices.get(in[i]);
				even[i] = indices.get(in[i]);
			} else {
				Integer higher = set.higher(in[i]);
				if (higher != null) odd[i] = indices.get(higher);
				Integer lower = set.lower(in[i]);
				if (lower != null) even[i] = indices.get(lower);
				set.add(in[i]);
			}
			indices.put(in[i], i);
		}
		int result = 1;
		for (int i = 0; i < n - 1; i++) {
			if (isPossible(i, true)) {
				result++;
			}
		}
		return result;
	}

	private boolean isPossible(int st, boolean isOdd) {
		if (st == n - 1) return true;
		MemoKey key = new MemoKey(st, isOdd);
		if (memo.containsKey(key)) return memo.get(key);
		if ((isOdd && odd[st] == -1) || (!isOdd && even[st] == -1)) {
			memo.put(key, false);
			return false;
		}
		boolean out = isPossible(isOdd ? odd[st] : even[st], !isOdd);
		memo.put(key, out);
		return out;
	}
}
