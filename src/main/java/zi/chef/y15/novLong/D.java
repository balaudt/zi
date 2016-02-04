package zi.chef.y15.novLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
 
public class D {
	static final int MAX_M = 1000;
	private static VertexMap vertices;
	private static BitSet curVisit;
	private static Counter counter;
	private static long ans;
 
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int m = Integer.parseInt(inStr[1]);
			if (m > 1000)
				throw new UnsupportedOperationException();
 
			counter = new Counter(n);
			inStr = reader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				counter.add(Integer.parseInt(inStr[j]) - 1);
			}
			//			System.out.println(counter);
 
			vertices = new VertexMap(MAX_M);
			Edge edges[] = new Edge[m];
			for (int j = 0; j < m; j++) {
				inStr = reader.readLine().split(" ");
				int v1 = Integer.parseInt(inStr[1]) - 1;
				int v2 = Integer.parseInt(inStr[2]) - 1;
				edges[j] = new Edge(Integer.parseInt(inStr[0]), v1, v2, j);
				vertices.add(v1);
				vertices.add(v2);
			}
			ArrayList<Integer> sortedVertices = new ArrayList<>(vertices.keySet());
			Collections.sort(sortedVertices, Collections.reverseOrder());
			Arrays.sort(edges);
			//			System.out.println(sortedVertices);
			//			System.out.println(Arrays.toString(edges));
 
			for (int j = 0; j < m; j++) {
				Edge edge = edges[j];
				vertices.get(edge.v1).edges.add(edge);
				vertices.get(edge.v2).edges.add(edge);
			}
 
			ans = 0;
			Iterator<Entry<Integer, Integer>> it = counter.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Integer, Integer> v = it.next();
				if (vertices.get(v.getKey()) == null) {
					ans += v.getValue() * (v.getKey() + 1);
					it.remove();
				}
			}
			//			System.out.println(ans);
 
			for (Integer maxVertex : sortedVertices) {
				if (counter.isEmpty())
					break;
				if (counter.containsKey(maxVertex)) {
					ans += counter.get(maxVertex) * (maxVertex + 1);
					counter.remove(maxVertex);
					if (counter.isEmpty())
						break;
				}
				curVisit = new BitSet(m);
				dfsVisit(maxVertex, MAX_M + 1, maxVertex);
			}
			System.out.println(ans);
		}
	}
 
	static void dfsVisit(int vertex, int day, int root) {
		if (counter.isEmpty())
			return;
		List<Edge> edges = vertices.get(vertex).edges;
		for (Edge edge : edges) {
			if (edge.day > day)
				break;
			if (curVisit.get(edge.id))
				continue;
			curVisit.set(edge.id);
			int v2 = edge.v(vertex);
			if (counter.containsKey(v2)) {
				ans += counter.get(v2) * (root + 1);
				counter.remove(v2);
				if (counter.isEmpty())
					return;
			}
			dfsVisit(v2, edge.day, root);
		}
	}
}
 
class DVertex {
	int v;
	List<Edge> edges;
 
	public DVertex(int v) {
		this.v = v;
		edges = new ArrayList<>();
	}
 
}
 
class Edge implements Comparable<Edge> {
	int day, v1, v2, id;
 
	public Edge(int day, int v1, int v2, int id) {
		super();
		this.day = day;
		this.v1 = v1;
		this.v2 = v2;
		this.id = id;
	}
 
	int v(int in) {
		if (v1 == in)
			return v2;
		else
			return v1;
	}
 
	@Override
	public int compareTo(Edge o) {
		if (day != o.day)
			return day - o.day;
		if (v1 != o.v1)
			return v1 - o.v1;
		return v2 - o.v2;
	}
 
	@Override
	public String toString() {
		return day + " " + v1 + " " + v2;
	}
}
 
class VertexMap extends HashMap<Integer, DVertex> {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	public VertexMap(int initialCapacity) {
		super(initialCapacity);
	}
 
	void add(int in) {
		DVertex vertex = get(in);
		if (vertex != null)
			return;
		put(in, new DVertex(in));
	}
}
 
class Counter extends HashMap<Integer, Integer> {
 
	private static final long serialVersionUID = 1L;
 
	public Counter(int initialCapacity) {
		super(initialCapacity);
	}
 
	void add(int in) {
		Integer ct = get(in);
		if (ct == null)
			put(in, 1);
		else
			put(in, ct + 1);
	}
} 