package zi.leet.easy;

import java.util.PriorityQueue;

//https://leetcode.com/problems/kth-largest-element-in-a-stream
public class KthLargest {

    PriorityQueue<Integer> q;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.q = new PriorityQueue<>();
        int i;
        for (i = 0; i < nums.length && i < k; i++) {
            q.add(nums[i]);
        }
        while (i < nums.length) {
            add(nums[i]);
            i++;
        }
    }

    public int add(int val) {
        if (q.size() < k)
            q.add(val);
        else if (q.peek() < val) {
            q.poll();
            q.add(val);
        }
        return q.peek();
    }
}
