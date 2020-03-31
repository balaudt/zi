package zi.leet.mock.apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/combination-sum/
public class CandidateSums {
	private Map<Integer, Set<List<Integer>>> memo;
	private int[] candidates;

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		memo = new HashMap<>();
		Arrays.sort(candidates);
		this.candidates = candidates;
		Set<List<Integer>> result = combinationSum(target);
		return new ArrayList<>(result);
	}

	private Set<List<Integer>> combinationSum(int n) {
		if (memo.containsKey(n)) return memo.get(n);
		Set<List<Integer>> out = new HashSet<>();
		for (int i = 0; i < candidates.length && candidates[i] <= n; i++) {
			if (candidates[i] == n) {
				out.add(Arrays.asList(n));
			} else if (candidates[i] < n) {
				Set<List<Integer>> subList = combinationSum(n - candidates[i]);
				for (List<Integer> l : subList) {
					ArrayList<Integer> current = new ArrayList<>();
					current.add(candidates[i]);
					current.addAll(l);
					Collections.sort(current);
					out.add(current);
				}
			}
		}
		memo.put(n, out);
		return out;
	}
}
