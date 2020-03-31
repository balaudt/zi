package zi.leet.mock.microsoft;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author balamurugan
 */
public class FreqStack {
	class Node implements Comparable<Node> {
		int value, frequency;
		Stack<Integer> indices;

		Node(int value) {
			this.value = value;
			indices = new Stack<>();
		}

		public boolean equals(Object node) {
			return ((Node) node).value == this.value;
		}

		public int hashCode() {
			return value;
		}

		public int compareTo(Node o) {
			if (o.frequency != frequency) return o.frequency - frequency;
			return o.indices.peek() - indices.peek();
		}
	}

	private PriorityQueue<Node> q;
	private Map<Integer, Node> lookup;
	private int index = 0;

	public FreqStack() {
		q = new PriorityQueue<>();
		lookup = new HashMap<>();
	}

	public void push(int x) {
		Node n = lookup.getOrDefault(x, new Node(x));
		q.remove(n);
		n.indices.push(index++);
		n.frequency++;
		lookup.put(x, n);
		q.offer(n);
	}

	public int pop() {
		Node n = q.poll();
		if (n.frequency > 1) {
			n.frequency--;
			n.indices.pop();
			q.offer(n);
		} else {
			lookup.remove(n.value);
		}
		return n.value;
	}
}
