package zi.leet.easy;

//https://leetcode.com/problems/maximize-distance-to-closest-person
public class ClosestPerson {
    public int maxDistToClosest(int[] seats) {
        int[] d = new int[seats.length];
        int last = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                d[i] = 0;
                last = i;
            } else if (last == -1)
                d[i] = Integer.MAX_VALUE;
            else
                d[i] = i - last;
        }
        last = -1;
        for (int i = seats.length - 1; i >= 0; i--) {
            if (seats[i] == 1) {
                d[i] = 0;
                last = i;
            } else if (last != -1)
                d[i] = Math.min(d[i], last - i);
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < seats.length; i++) result = Math.max(result, d[i]);
        return result;
    }

}
