package zi.leet.easy;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/number-of-recent-calls/
public class RecentCounter {
    private Deque<Integer> times = new ArrayDeque<>();

    public RecentCounter() {

    }

    public int ping(int t) {
        times.offer(t);
        while (times.peek() < t - 3000) {
            times.poll();
        }
        return times.size();
    }

    public static void main(String[] args) {
        RecentCounter counter = new RecentCounter();
        System.out.println(counter.ping(1));
        System.out.println(counter.ping(100));
        System.out.println(counter.ping(3001));
        System.out.println(counter.ping(3002));
    }
}
