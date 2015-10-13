package zi.chef.y15.augLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class H {

	static ArrayList<Vertex> vertices;
	static SortedArrayList sortedLens;
	static int ans, minLenInGraph;
	static byte l, r;

	public static void main(String[] args) throws Exception {
		//		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
		try (BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/21/G.in"))) {
			int t = Integer.parseInt(reader.readLine());
			if (t > 1000) {
				throw new UnsupportedOperationException();
			}
			String inStr[];
			for (int i = 0; i < t; i++) {
				inStr = reader.readLine().split(" ");
				int n = Integer.parseInt(inStr[0]);
				if (n > 1000) {
					throw new UnsupportedOperationException();
				}
				l = Byte.parseByte(inStr[1]);
				r = Byte.parseByte(inStr[2]);
				sortedLens = new SortedArrayList(r);
				ans = Integer.MAX_VALUE;
				vertices = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					vertices.add(new Vertex(j));
				}
				minLenInGraph = Integer.MAX_VALUE;
				for (int j = 0; j < n - 1; j++) {
					inStr = reader.readLine().split(" ");
					int a = Integer.parseInt(inStr[0]) - 1;
					int b = Integer.parseInt(inStr[1]) - 1;
					int c = Integer.parseInt(inStr[2]);
					vertices.get(a).adjList.put(b, c);
					vertices.get(b).adjList.put(a, c);
					if (c < minLenInGraph)
						minLenInGraph = c;
				}
				boolean flag = false;
				for (Vertex u : vertices) {
					if (flag)
						break;
					for (Vertex v : vertices) {
						v.reset();
					}
//					dfsVisit(u);
					traverse(u);
				}
				System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
			}
		}
	}

	static void traverse(Vertex u) {
		ArrayDeque<Entry<Integer, Integer>> trails = new ArrayDeque<>();
		ArrayDeque<Vertex> trailHelper = new ArrayDeque<>();
		u.c = Color.GREY;
		System.out.println("->"+u.no);
        trails.push(u.nextSibling());
		trailHelper.push(u);
		while (!trails.isEmpty()) {
			Entry<Integer, Integer> current = trails.peek();
			Vertex reachedNode = vertices.get(current.getKey());
			Entry<Integer, Integer> next = null;
			//Start Enter node
			if (reachedNode.c != Color.WHITE)
				next = null;
			else {
				next = reachedNode.nextSibling();
				System.out.println(next.getKey());
				Integer edgeLen = current.getValue();
				sortedLens.add(edgeLen);
			}
			//End Enter node
			while (next == null && !trails.isEmpty()) {
				Entry<Integer, Integer> top = trails.pop();
				Vertex v = trailHelper.pop();
				//Start ExitNode
				sortedLens.remove();
				vertices.get(top.getValue()).c = Color.BLACK;
				//End ExitNode

				//UndoTransition(top)
				next = v.nextSibling();
				reachedNode = v;
			}
			if (next != null) {
				trails.push(next);
				trailHelper.push(reachedNode);
			}
		}
		u.c = Color.BLACK;
	}

	static boolean dfsVisit(Vertex u) {
		if (sortedLens.curSize == r)
			return false;
		u.c = Color.GREY;
		Map<Integer, Integer> adjList = u.adjList;
		for (Entry<Integer, Integer> edge : adjList.entrySet()) {
			Vertex vVertex = vertices.get(edge.getKey());
			if (vVertex.c == Color.WHITE) {
				Integer edgeLen = edge.getValue();
				if (sortedLens.add(edgeLen))
					return true;
				dfsVisit(vVertex);
			}
		}
		u.c = Color.BLACK;
		sortedLens.remove();
		return false;
	}

	static int globalInsCt = 0;

	static class SortedArrayList {
		ArrayList<GNum> sortedByNum;
		ArrayDeque<GNum> q;
		byte curSize = 0;

		public SortedArrayList(int n) {
			sortedByNum = new ArrayList<>(n);
			q = new ArrayDeque<>();
		}

		public boolean add(int no) {
			GNum num = new GNum(no, globalInsCt++);
			int ip = Collections.binarySearch(sortedByNum, num, GNum.NUMBER_COMPARATOR);
			if (ip < 0)
				ip = -ip - 1;
			sortedByNum.add(ip, num);
			q.add(num);
			curSize++;
			if (curSize >= l) {
				int t = curSize;
				Deque<Integer> posStack = new ArrayDeque<>(curSize);
				Deque<GNum> numStack = new ArrayDeque<>(curSize);
				Iterator<GNum> it = q.iterator();
				while (t >= l) {
					int median = findMedian(t);
					if (median < ans) {
						ans = median;
						if (ans == minLenInGraph) {
							return true;
						}
					}
					ip = Collections.binarySearch(sortedByNum, it.next(), GNum.NUMBER_COMPARATOR);
					posStack.push(ip);
					numStack.push(sortedByNum.remove(ip));
					t--;
				}
				while (!numStack.isEmpty()) {
					sortedByNum.add(posStack.pop(), numStack.pop());
				}
			}
			return false;
		}

		private int findMedian(int tSize) {
			if (tSize % 2 == 0) {
				int t = tSize / 2;
				return Integer.max(sortedByNum.get(t).no, sortedByNum.get(t - 1).no);
			}
			return sortedByNum.get(tSize / 2).no;
		}

		public void remove() {
			sortedByNum.remove(q.pop());
		}

	}
}

class Vertex {
	int no;
	Map<Integer, Integer> adjList = new HashMap<>();
	Color c = Color.WHITE;
	Iterator<Entry<Integer, Integer>> it;

	public Vertex(int no) {
		this.no = no;
	}

	public void reset() {
		it = adjList.entrySet().iterator();
		c = Color.WHITE;
	}

	public Entry<Integer, Integer> nextSibling() {
		if (it.hasNext())
			return it.next();
		return null;
	}
}

enum Color {
	WHITE, GREY, BLACK;
}

class GNum {
	int no;
	int insertPos;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + insertPos;
		result = prime * result + no;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GNum other = (GNum) obj;
		if (insertPos != other.insertPos)
			return false;
		if (no != other.no)
			return false;
		return true;
	}

	public GNum(int no, int insertPos) {
		this.no = no;
		this.insertPos = insertPos;
	}

	static final Comparator<GNum> NUMBER_COMPARATOR = (GNum l, GNum r) -> {
		return l.no - r.no;
	};
	static final Comparator<GNum> INDEX_COMPARATOR = (GNum l, GNum r) -> {
		return l.insertPos - r.insertPos;
	};

	@Override
	public String toString() {
		return "G[n=" + no + ",i=" + insertPos + "]";
	}

}