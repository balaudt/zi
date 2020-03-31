package zi.leet.ds;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	public static ListNode createList(int[] in) {
		ListNode node = new ListNode(in[0]);
		ListNode first = node;
		for (int i = 1; i < in.length; i++) {
			ListNode next = new ListNode(in[i]);
			node.next = next;
			node = next;
		}
		return first;
	}

	@Override
	public String toString() {
		return val + "->" + next;
	}
}

