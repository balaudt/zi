package zi.leet.google;

import zi.leet.ds.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author balamurugan
 */
public class KSortedLists {

	public ListNode mergeKLists(ListNode[] lists) {
		int nullCount = 0;
		int n = lists.length;
		PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(n1 -> n1.val));
		for (ListNode list : lists) {
			if (list == null) {
				nullCount++;
			} else {
				heap.add(list);
			}
		}
		if (nullCount == n) {
			return null;
		}
		ListNode out = heap.poll();
		if (out.next == null) {
			nullCount++;
		} else {
			heap.add(out.next);
		}
		ListNode next = out;
		while (nullCount < n) {
			ListNode min = heap.poll();
			if (min.next == null) {
				nullCount++;
			} else {
				heap.add(min.next);
			}
			next.next = min;
			next = min;
		}
		return out;
	}

	private ListNode listFromArray(int[] in) {
		ListNode root = new ListNode(in[0]);
		ListNode next = root;
		for (int i = 1; i < in.length; i++) {
			next.next = new ListNode(in[i]);
			next = next.next;
		}
		return root;
	}

	private void printList(ListNode in) {
		while (in != null) {
			System.out.println(in.val);
			in = in.next;
		}
	}

	public static void main(String[] args) {
		KSortedLists kSortedLists = new KSortedLists();
		ListNode listNode = kSortedLists.mergeKLists(new ListNode[]{kSortedLists.listFromArray(new int[]{1, 4, 5}),
				kSortedLists.listFromArray(new int[]{1, 3, 4}),
				kSortedLists.listFromArray(new int[]{2, 6})});
		kSortedLists.printList(listNode);
	}
}
