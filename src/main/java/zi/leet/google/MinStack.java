package zi.leet.google;

/**
 * @author balamurugan
 */
public class MinStack {
	class Node {
		int x;
		int min;
		Node next;
	}

	private Node head;

	public void push(int x) {
		if (head == null) {
			head = new Node();
			head.x = x;
			head.min = x;
		} else {
			Node node = new Node();
			node.next = head;
			node.x = x;
			node.min = Math.min(x, head.min);
			head = node;
		}
	}

	public void pop() {
		head = head.next;
	}

	public int top() {
		return head.x;
	}

	public int getMin() {
		return head.min;
	}

	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin());   //--> Returns -3.
		minStack.pop();
		System.out.println(minStack.top());      //--> Returns 0.
		System.out.println(minStack.getMin());   //--> Returns -2.
	}
}
