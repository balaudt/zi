package zi.leet.mock.fb;

import zi.leet.ds.ListNode;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/add-two-numbers/
public class AddNumbersInList {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode head = null, node = null;
		while (l1 != null || l2 != null) {
			int sum = carry;
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}
			if (node == null) {
				node = new ListNode(sum % 10);
				head = node;
			} else {
				node.next = new ListNode(sum % 10);
				node = node.next;
			}
			carry = sum / 10;
		}
		if (carry != 0) node.next = new ListNode(carry);
		return head;
	}
}
