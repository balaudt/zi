package zi.leet.fb;

import java.util.Iterator;

/**
 * @author balamurugan
 */
public class List implements Iterable<Integer> {

	private class ListIterator implements Iterator<Integer> {
		Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Integer next() {
			int value = current.value;
			current = current.next;
			return value;
		}

	}

	@Override
	public Iterator<Integer> iterator() {
		return new ListIterator();
	}

	class Node {
		int value;
		Node next;

		Node(int value) {
			this.value = value;
		}
	}

	Node head;
	Node tail;

	void append(int n) {
		Node newNode = new Node(n);
		if (head != null) {
			tail.next = newNode;
			tail = newNode;
		} else {
			head = newNode;
			tail = newNode;
		}
	}

	void append(List l) {
		if (head != null) {
			tail.next = l.head;
			tail = l.tail;
		} else {
			head = l.head;
			tail = l.tail;
		}
	}

	boolean isEmpty() {
		return head == null;
	}


}
