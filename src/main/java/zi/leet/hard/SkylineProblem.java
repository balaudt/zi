package zi.leet.hard;

import java.util.*;

//https://leetcode.com/problems/the-skyline-problem
public class SkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeMap<Integer, List<Map.Entry<Integer, Boolean>>> eventPoints = new TreeMap<>();
        for (int[] building : buildings) {
            List<Map.Entry<Integer, Boolean>> left = eventPoints.getOrDefault(building[0], new ArrayList<>());
            left.add(new AbstractMap.SimpleEntry<>(building[2], true));
            eventPoints.put(building[0], left);

            List<Map.Entry<Integer, Boolean>> right = eventPoints.getOrDefault(building[1], new ArrayList<>());
            right.add(new AbstractMap.SimpleEntry<>(building[2], false));
            eventPoints.put(building[1], right);
        }
        PriorityQueue<Integer> heights = new PriorityQueue<>(Collections.reverseOrder());
        heights.add(0);
        int last = 0;
        List<List<Integer>> results = new ArrayList<>();
        for (Map.Entry<Integer, List<Map.Entry<Integer, Boolean>>> entry : eventPoints.entrySet()) {
            Integer x = entry.getKey();
            List<Map.Entry<Integer, Boolean>> events = entry.getValue();
            for (Map.Entry<Integer, Boolean> event : events) {
                if (event.getValue()) {
                    heights.add(event.getKey());
                } else {
                    heights.remove(event.getKey());
                }
            }
            if (heights.peek() != last) {
                results.add(Arrays.asList(x, heights.peek()));
                last = heights.peek();
            }
        }
        return results;
    }
}
