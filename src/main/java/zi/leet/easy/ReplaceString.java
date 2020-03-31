package zi.leet.easy;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class ReplaceString {
	class Op implements Comparable<Op> {
		int index;
		String source, target;

		public Op(int index, String source, String target) {
			this.index = index;
			this.source = source;
			this.target = target;
		}

		@Override
		public int compareTo(Op o) {
			return index - o.index;
		}
	}

	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
		Op[] ops = new Op[indexes.length];
		for (int i = 0; i < indexes.length; i++) {
			ops[i] = new Op(indexes[i], sources[i], targets[i]);
		}
		Arrays.sort(ops);
		int last = -1;
		StringBuilder b = new StringBuilder();
		char[] s = S.toCharArray();
		for (int i = 0; i < ops.length; i++) {
			int cur = ops[i].index;
			if (cur > last + 1)
				b.append(S, last + 1, cur);
			boolean doesMatch = true;
			int j = 0;
			for (j = 0; j < ops[i].source.length() && cur + j < s.length; j++)
				if (ops[i].source.charAt(j) != s[cur + j]) {
					doesMatch = false;
					break;
				}
			doesMatch &= (j == ops[i].source.length());
			if (doesMatch) {
				b.append(ops[i].target);
				last = cur + j - 1;
			} else {
				last = cur - 1;
			}
		}
		if (last != s.length - 1) b.append(S, last + 1, s.length);
		return b.toString();
	}
}
