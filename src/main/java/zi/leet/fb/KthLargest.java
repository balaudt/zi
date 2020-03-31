package zi.leet.fb;

import java.util.PriorityQueue;

/**
 * @author balamurugan
 */
public class KthLargest {
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			queue.add(nums[i]);
		}
		for (int i = k; i < nums.length; i++) {
			Integer smallest = queue.peek();
			if (nums[i] > smallest) {
				queue.poll();
				queue.add(nums[i]);
			}
		}
		return queue.poll();
	}

	public static void main(String[] args) {
		KthLargest kthLargest = new KthLargest();
		System.out.println(kthLargest.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
		System.out.println(kthLargest.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
	}
}
