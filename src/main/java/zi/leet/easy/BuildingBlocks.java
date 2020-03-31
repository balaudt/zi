package zi.leet.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author balamurugan
 */
public class BuildingBlocks {
	class Key {
		int r, workers;

		public Key(int r, int workers) {
			this.r = r;
			this.workers = workers;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Key key = (Key) o;
			return r == key.r &&
					workers == key.workers;
		}

		@Override
		public String toString() {
			return r + "-" + workers;
		}

		@Override
		public int hashCode() {
			return Objects.hash(r, workers);
		}
	}

	private int[] blocks;
	private int split;
	private Map<Key, Integer> memo;

	public int minBuildTime(int[] blocks, int split) {
		Arrays.sort(blocks);
		this.blocks = blocks;
		this.split = split;
		memo = new HashMap<>();
		int result = minBuildTime(blocks.length - 1, 1);
//		System.out.println(memo);
		return result;
	}

	private int minBuildTime(int r, int workers) {
		if (r < 0)
			return 0;
		if (workers >= r + 1)
			return blocks[r];
		Key key = new Key(r, workers);
		if (memo.containsKey(key))
			return memo.get(key);
		int result = Integer.MAX_VALUE;
		for (int i = 1; i <= workers; i++) {
			result = Math.min(result, Math.max(minBuildTime(r - workers + i, 2 * i) + split, ((i == workers) ? 0 : blocks[r])));
		}
		memo.put(key, result);
		return result;
	}
}
