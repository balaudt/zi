package zi.leet.easy;

import zi.leet.ds.ListNode;

/**
 * @author balamurugan
 */
public class ReorderList {
	public void reorderList(ListNode head) {
		ListNode slow = head, fast = head;
		int ct = 0;
		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
			ct++;
			if (fast != null) {
				fast = fast.next;
				ct++;
			}
		}
		ListNode latter = reverse(slow);

		ListNode prev = null, node = head;
		while (node != slow) {
			prev = node;
			node = node.next;
		}
		prev.next = null;

		ListNode f = head, b = latter;
		for (int i = 0; i < ct / 2; i++) {
			ListNode ft = f.next, bt = b.next;
			f.next = b;
			b.next = ft;
			f = ft;
			b = bt;
		}
		System.out.println(head);
	}

	private ListNode reverse(ListNode node) {
		ListNode prev = null, current = node;
		while (current != null) {
			ListNode t = current.next;
			current.next = prev;
			prev = current;
			current = t;
		}
		return prev;
	}
}
