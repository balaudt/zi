package zi.leet.easy;

import java.util.TreeSet;

/**
 * @author balamurugan
 */
public class MyCalendarTwo {
	class Event implements Comparable<Event> {
		int start, end;

		Event(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(Event o) {
			if (start != o.start) return start - o.start;
			return end - o.end;
		}
	}

	private TreeSet<Event> currentEvents = new TreeSet<>();

	public MyCalendarTwo() {

	}

	public boolean book(int start, int end) {
		Event e = new Event(start, end);
		if (currentEvents.size() < 2) {
			currentEvents.add(e);
			return true;
		}
		Event l = currentEvents.lower(e);
		Event h = currentEvents.higher(e);
		if (doesOverlap(l, e) && doesOverlap(e, h)) return false;
		if (doesOverlap(l, e) && doesOverlap(currentEvents.lower(l), l)) return false;
		if (doesOverlap(e, h) && doesOverlap(h, currentEvents.higher(h))) return false;
		currentEvents.add(e);
		return true;
	}

	private boolean doesOverlap(Event e1, Event e2) {
		if (e1 == null || e2 == null) return false;
		if (e2.start < e1.end) return true;
		else return false;
	}
}
