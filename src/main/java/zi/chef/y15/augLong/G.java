package zi.chef.y15.augLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class G {

	static ArrayList<Vertex> vertices;
	static SortedArrayList sortedLens;
	static int ans, minLenInGraph, curDfsNode;
	static byte l, r;

	public static void main(String[] args) throws Exception {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//		try (BufferedReader reader = new BufferedReader(new FileReader("C:/ft/200/G-gen.in"))) {
			int t = Integer.parseInt(reader.readLine());
			/*if (t > 1000) {
				throw new UnsupportedOperationException();
			}*/
			String inStr[];
			for (int i = 0; i < t; i++) {
				inStr = reader.readLine().split(" ");
				int n = Integer.parseInt(inStr[0]);
				/*if (n > 1000) {
					throw new UnsupportedOperationException();
				}*/
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
						v.c = Color.WHITE;
					}
					sortedLens.clear();
					curDfsNode = u.no;
					flag = dfsVisit(u);
				}
				System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
			}
		}
	}

	static boolean dfsVisit(Vertex u) {
		if (sortedLens.curSize == r) {
			u.c = Color.BLACK;
			sortedLens.remove();
			return false;
		}
		u.c = Color.GREY;
		Map<Integer, Integer> adjList = u.adjList;
		for (Entry<Integer, Integer> edge : adjList.entrySet()) {
			Vertex vVertex = vertices.get(edge.getKey());
			if (vVertex.c == Color.WHITE) {
				Integer edgeLen = edge.getValue();
				if (sortedLens.add(edgeLen))
					return true;
				if (dfsVisit(vVertex))
					return true;
			}
		}
		u.c = Color.BLACK;
		if (u.no != curDfsNode) {
			sortedLens.remove();
		}
		return false;
	}

	static int globalInsCt = 0;

	static class SortedArrayList {
		ArrayList<GNum> sortedByNum;

		@Override
		public String toString() {
			return sortedByNum.toString();
		}

		ArrayDeque<GNum> q;
		byte curSize = 0;

		public SortedArrayList(int n) {
			sortedByNum = new ArrayList<>(n);
			q = new ArrayDeque<>();
		}

		public void clear() {
			curSize = 0;
			q.clear();
			sortedByNum.clear();
		}

		public boolean add(int no) {
			GNum num = new GNum(no, globalInsCt++);
			int ip = Collections.binarySearch(sortedByNum, num, GNum.NUMBER_COMPARATOR);
			if (ip < 0)
				ip = -ip - 1;
			sortedByNum.add(ip, num);
			q.push(num);
			curSize++;
			if (curSize >= l) {
				int t = curSize;
				Deque<Integer> posStack = new ArrayDeque<>(curSize);
				Deque<GNum> numStack = new ArrayDeque<>(curSize);
				Iterator<GNum> it = q.descendingIterator();
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
			curSize--;
		}

	}
}

