package zi.leet.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author balamurugan
 */
public class MinRectangle {
	class Line {
		int x, len;

		Line(int x, int len) {
			this.x = x;
			this.len = len;
		}

		public boolean equals(Object o) {
			if (!(o instanceof Line)) return false;
			Line other = (Line) o;
			return x == other.x && len == other.len;
		}

		public int hashCode() {
			int result = 1;
			result = result * 31 + x;
			result = result * 31 + len;
			return result;
		}
	}

	public int minAreaRect(int[][] points) {
		long ct = System.currentTimeMillis();
		Map<Line, TreeSet<Integer>> xAlignLines = new HashMap<>();
		int result = Integer.MAX_VALUE;
		for (int[] p1 : points) {
			for (int[] p2 : points) {
				if (p1[1] == p2[1] && p1[0] != p2[0]) {
					int x1 = Math.min(p1[0], p2[0]);
					int len = Math.abs(p1[0] - p2[0]);
					Line key = new Line(x1, len);
					TreeSet<Integer> curYs = xAlignLines.getOrDefault(key, new TreeSet<>());
					Integer t = curYs.lower(p1[1]);
					if (t != null) result = Math.min(result, (p1[1] - t) * len);
					t = curYs.higher(p1[1]);
					if (t != null) result = Math.min(result, (t - p1[1]) * len);
					curYs.add(p1[1]);
					xAlignLines.put(key, curYs);
				}
			}
		}
		System.out.println(System.currentTimeMillis() - ct);
		return result == Integer.MAX_VALUE ? 0 : result;
	}
}
