package zi.leet.google;

import java.util.TreeSet;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/maximize-distance-to-closest-person/
public class SeatSelection {
	public int maxDistToClosest(int[] seats) {
		TreeSet<Integer> nonEmptySeats = new TreeSet<>();
		for (int i = 0; i < seats.length; i++) {
			if (seats[i] == 1) {
				nonEmptySeats.add(i);
			}
		}
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < seats.length; i++) {
			if (seats[i] == 0) {
				Integer l = nonEmptySeats.lower(i);
				l = (l == null) ? Integer.MAX_VALUE : i - l;
				Integer r = nonEmptySeats.higher(i);
				r = (r == null) ? Integer.MAX_VALUE : r - i;
				int closest = Math.min(l, r);
				result = Math.max(result, closest);
			}
		}
		return result;
	}
}
