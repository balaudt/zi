package zi.leet.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/perfect-rectangle/
public class PerfectRectangle {
	class Event {
		int y1, y2;
		boolean isStart;

		public Event(int y1, int y2, boolean isStart) {
			this.y1 = y1;
			this.y2 = y2;
			this.isStart = isStart;
		}
	}

	public boolean isRectangleCover(int[][] rectangles) {
		if (rectangles == null || rectangles.length == 0) return true;
		TreeMap<Integer, List<Event>> eventSchedule = new TreeMap<>();
		for (int[] rectangle : rectangles) {
			List<Event> events = eventSchedule.getOrDefault(rectangle[0], new ArrayList<>());
			events.add(new Event(rectangle[1], rectangle[3], true));
			eventSchedule.put(rectangle[0], events);

			events = eventSchedule.getOrDefault(rectangle[2], new ArrayList<>());
			events.add(new Event(rectangle[1], rectangle[3], false));
			eventSchedule.put(rectangle[2], events);
		}
		List<List<Event>> allEvents = new ArrayList<>(eventSchedule.values());
		Comparator<Event> eventComparator = (o1, o2) -> {
			if (o1.isStart != o2.isStart) return o1.isStart ? -1 : 1;
			if (o1.y1 != o2.y1) return o1.y1 - o2.y1;
			return o1.y2 - o2.y2;
		};
		allEvents.forEach(events -> events.sort(eventComparator));
		List<Event> initialEvents = allEvents.get(0);
		int last = initialEvents.get(0).y1;
		for (int i = 0; i < initialEvents.size(); i++) {
			Event event = initialEvents.get(i);
			if (event.y1 != last) return false;
			last = event.y2;
		}
		for (int i = 1; i < allEvents.size() - 1; i++) {
			List<Event> currentEvents = allEvents.get(i);
			List<List<Integer>> intervals = new ArrayList<>();
			int j = 0, n = currentEvents.size(), y1;
			while (j < n && currentEvents.get(j).isStart) {
				last = currentEvents.get(j).y1;
				y1 = last;
				while (j < n && currentEvents.get(j).isStart && currentEvents.get(j).y1 == last) {
					last = currentEvents.get(j).y2;
					j++;
				}
				intervals.add(Arrays.asList(y1, currentEvents.get(j - 1).y2));
			}

			int k = 0;
			while (j < n) {
				last = currentEvents.get(j).y1;
				y1 = last;
				while (j < n && currentEvents.get(j).y1 == last) {
					last = currentEvents.get(j).y2;
					j++;
				}
				if (k >= intervals.size() || !intervals.get(k).equals(Arrays.asList(y1, currentEvents.get(j - 1).y2)))
					return false;
				k++;
			}
			if (k != intervals.size()) return false;
		}
		return true;
	}

}
