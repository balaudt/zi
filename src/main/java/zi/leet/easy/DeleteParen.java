package zi.leet.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author balamurugan
 */
public class DeleteParen {
	private List<String> out;
	private List<List<String>> subparts;

	public List<String> removeInvalidParentheses(String s) {
		char[] in = s.toCharArray();
		int st = 0;
		while (st < in.length && in[st] == ')') st++;
		if (st == in.length) return Collections.emptyList();
		int end = in.length - 1;
		while (end >= 0 && in[end] == '(') end--;
		if (end < 0) return Collections.emptyList();

		out = new ArrayList<>();
		subparts = new ArrayList<>();
		int brackets = 0, lastStart = st;
		for (int i = st; i <= end; i++) {
			if (in[i] == '(') brackets++;
			else if (in[i] == ')') brackets--;
			if (brackets < 0) {
				List<String> subpart = computeSubpart(in, lastStart, i, true);
				if (subpart != null)
					subparts.add(subpart);
				brackets = 0;
				lastStart = i + 1;
			}
		}
		if (lastStart <= end)
			if (brackets == 0)
				subparts.add(Collections.singletonList(new String(in, lastStart, end - lastStart + 1)));
			else
				subparts.add(computeSubpart(in, lastStart, end, false));
		crossProduct(new String[subparts.size()], 0);
		return out;
	}

	private List<String> computeSubpart(char[] in, int st, int end, boolean delClose) {
		if (st == end) return null;
		Set<String> results = new HashSet<>();
		for (int i = st; i <= end; i++) {
			if (in[i] == '(' && !delClose)
				if (i == st)
					results.add(new String(in, st + 1, end - st));
				else
					results.add(new String(in, st, i - st) + new String(in, i + 1, end - i));
			else if (in[i] == ')' && delClose)
				if (i == end)
					results.add(new String(in, st, end - st));
				else
					results.add(new String(in, st, i - st) + new String(in, i + 1, end - i));
		}
		return new ArrayList<>(results);
	}

	private void crossProduct(String[] res, int st) {
		if (res.length == st) {
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < res.length; i++) {
				b.append(res[i]);
			}
			out.add(b.toString());
			return;
		}
		List<String> possibilities = subparts.get(st);
		for (String possibility : possibilities) {
			res[st] = possibility;
			crossProduct(res, st + 1);
		}
	}
}
