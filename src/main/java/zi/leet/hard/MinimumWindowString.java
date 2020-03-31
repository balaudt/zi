package zi.leet.hard;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/minimum-window-substring/
public class MinimumWindowString {
	public String minWindow(String s, String t) {
		Map<Character, Integer> pFrequencies = new HashMap<>();
		Set<Character> p = new HashSet<>();
		for (char c : t.toCharArray()) {
			p.add(c);
			Integer ct = pFrequencies.getOrDefault(c, 0);
			pFrequencies.put(c, ct + 1);
		}
		Map<Character, LinkedList<Integer>> characterPositions = new HashMap<>();
		PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
		int n = s.length();
		int minLen = Integer.MAX_VALUE, minStart = -1, st = -1;
		boolean didCover = false;
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			if (!p.contains(c)) continue;
			if (!characterPositions.containsKey(c)) {
				LinkedList<Integer> positions = new LinkedList<>();
				positions.add(i);
				characterPositions.put(c, positions);
				q.add(new AbstractMap.SimpleEntry<>(c, i));
				if (st == -1) {
					st = i;
				}
			} else {
				characterPositions.get(c).add(i);
				if (q.peek().getKey().equals(c)) {
					st = optimize(q, characterPositions, pFrequencies);
				}
			}
			didCover = didCover || didCover(pFrequencies, characterPositions);
			if (didCover && i - st + 1 < minLen) {
				minLen = i - st + 1;
				minStart = st;
			}
		}
		return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
	}

	private boolean didCover(Map<Character, Integer> pFrequencies, Map<Character, LinkedList<Integer>> characterPositions) {
		if (pFrequencies.size() != characterPositions.size()) return false;
		return pFrequencies.entrySet().stream()
				.noneMatch(e -> characterPositions.get(e.getKey()).size() < e.getValue());
	}

	private int optimize(PriorityQueue<Map.Entry<Character, Integer>> q,
						 Map<Character, LinkedList<Integer>> characterPositions, Map<Character, Integer> pFrequencies) {
		while (characterPositions.get(q.peek().getKey()).size() > pFrequencies.get(q.peek().getKey())) {
			Map.Entry<Character, Integer> entry = q.poll();
			LinkedList<Integer> positions = characterPositions.get(entry.getKey());
			Integer charCt = pFrequencies.get(entry.getKey());
			while (positions.size() > charCt) positions.remove(0);
			q.offer(new AbstractMap.SimpleEntry<>(entry.getKey(), positions.get(0)));
		}
		return q.peek().getValue();
	}

}
