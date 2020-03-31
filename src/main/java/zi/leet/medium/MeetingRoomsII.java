package zi.leet.medium;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        Comparator<int[]> intervalComparator = (o1, o2) -> {
            if (o1[0] != o1[1]) return o1[0] - o2[0];
            else return o2[1] - o2[0];
        };
        Arrays.sort(intervals, intervalComparator);

        int result = 1;
        int st = 0;
        for (int i = 1; i < intervals.length; i++) {
            while (i < intervals.length && doesOverlap(intervals[i], intervals[i - 1])) i++;
            result = Math.max(result, i - st);
            st++;
        }
        return result;
    }

    private boolean doesOverlap(int[] o1, int[] o2) {
        return o2[0] < o1[1];
    }

}
