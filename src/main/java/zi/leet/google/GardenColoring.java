package zi.leet.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/flower-planting-with-no-adjacent/
public class GardenColoring {
	public int[] gardenNoAdj(int N, int[][] paths) {
		int[] result = new int[N];
		Map<Integer, List<Integer>> adjList = new HashMap<>();
		for (int i = 0; i < N; i++) {
			adjList.put(i + 1, new ArrayList<>());
		}
		for (int[] path : paths) {
			adjList.get(path[0]).add(path[1]);
			adjList.get(path[1]).add(path[0]);
		}
		List<Integer> masterColors = Arrays.asList(1, 2, 3, 4);
		for (int i = 0; i < N; i++) {
			Set<Integer> possibleColors = new HashSet<>(masterColors);
			adjList.get(i + 1).forEach(c -> possibleColors.remove(result[c - 1]));
			result[i] = possibleColors.iterator().next();
		}
		return result;
	}
}
