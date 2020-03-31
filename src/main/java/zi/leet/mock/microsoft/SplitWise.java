package zi.leet.mock.microsoft;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author balamurugan
 */
public class SplitWise {
	private List<Integer> posNets, negNets;
	private Map<Map.Entry<List<Integer>, List<Integer>>, Integer> memo;
	private int output = Integer.MAX_VALUE;

	public int minTransfers(int[][] transactions) {
		Map<Integer, Integer> personNets = new HashMap<>();
		for (int[] t : transactions) {
			int giverNet = personNets.getOrDefault(t[0], 0);
			giverNet += t[2];
			personNets.put(t[0], giverNet);

			int getterNet = personNets.getOrDefault(t[1], 0);
			getterNet -= t[2];
			personNets.put(t[1], getterNet);
		}
		posNets = personNets.values().stream().filter(x -> x > 0).collect(Collectors.toList());
		negNets = personNets.values().stream().filter(x -> x < 0)
				.map(x -> -x).collect(Collectors.toList());
		memo = new HashMap<>();
		return backtrack(0, 0, 0);
	}

	private int backtrack(int pVisit, int nVisit, int transCount) {
		if (transCount >= output) return posNets.size() + negNets.size();
		if (pVisit == (1 << posNets.size()) - 1 && nVisit == (1 << negNets.size()) - 1) return 0;
		AbstractMap.SimpleEntry<List<Integer>, List<Integer>> key = new AbstractMap.SimpleEntry<>(new ArrayList<>(posNets), new ArrayList<>(negNets));
		if (memo.containsKey(key)) return memo.get(key);
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < posNets.size(); i++) {
			if (posNets.get(i) == 0) continue;
			for (int j = 0; j < negNets.size(); j++) {
				if (negNets.get(j) == 0) continue;
				int pt = posNets.get(i), nt = negNets.get(j);
				if (posNets.get(i) > negNets.get(j)) {
					posNets.set(i, posNets.get(i) - negNets.get(j));
					negNets.set(j, 0);
					nVisit |= 1 << j;
					result = Math.min(result, backtrack(pVisit, nVisit, transCount + 1));
				} else if (posNets.get(i) < negNets.get(j)) {
					negNets.set(j, negNets.get(j) - posNets.get(i));
					posNets.set(i, 0);
					pVisit |= 1 << i;
					result = Math.min(result, backtrack(pVisit, nVisit, transCount + 1));
				} else {
					posNets.set(i, 0);
					negNets.set(j, 0);
					nVisit |= 1 << j;
					pVisit |= 1 << i;
					result = Math.min(result, backtrack(pVisit, nVisit, transCount + 1));
				}
				posNets.set(i, pt);
				negNets.set(j, nt);
				pVisit &= ~(1 << i);
				nVisit &= ~(1 << j);
			}
			if (pVisit == 0 && nVisit == 0) output = result;
		}
		memo.put(key, result + 1);
		return result + 1;
	}
}

