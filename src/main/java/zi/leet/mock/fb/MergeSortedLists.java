package zi.leet.mock.fb;

import zi.leet.ds.ListNode;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeSortedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode out = null, node = null;
		while (l1 != null || l2 != null) {
			ListNode t = null;
			if (l1 != null && l2 != null) {
				if (l1.val < l2.val) {
					t = l1;
					l1 = l1.next;
				} else {
					t = l2;
					l2 = l2.next;
				}
			} else if (l1 == null) {
				t = l2;
				l2 = l2.next;
			} else {
				t = l1;
				l1 = l1.next;
			}
			if (node == null) {
				node = new ListNode(t.val);
				out = node;
			} else {
				node.next = new ListNode(t.val);
				node = node.next;
			}
		}
		return out;
	}
}
