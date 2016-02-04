package zi.chef.y15.decLong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class D {
	static final short S = 10;
	static final int P = (int) (1e9 + 7);
	static final short FILTER_MAX = (short) Math.pow(2, S);
	static final short MASK = (short) (FILTER_MAX - 1);
	static int edgeCount;
	static int twoPows[] = new int[17];
	static long ans;
	static Map<Short, FilterVertex> vertices;
	static FilterMap filterCts;
	static BitSet visitedVertices;

	public static void main(String[] args) throws Exception {
		twoPows[0] = 2;
		for (int i = 1; i < twoPows.length; i++) {
			twoPows[i] = (int) (((long) twoPows[i - 1] * twoPows[i - 1]) % P);
		}

		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/dec/D-gen.in"));
		//				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			char[] inChars = reader.readLine().toCharArray();
			short in = 0, ct = 1;
			for (int j = inChars.length - 1; j >= 0; j--) {
				in += ct * (inChars[j] == 'b' ? 1 : 0);
				ct *= 2;
			}
			int n = Integer.parseInt(reader.readLine());
			filterCts = new FilterMap(FILTER_MAX);
			for (int j = 0; j < n; j++) {
				inChars = reader.readLine().toCharArray();
				short filter = 0;
				ct = 1;
				for (int k = inChars.length - 1; k >= 0; k--) {
					filter += ct * (inChars[k] == '+' ? 1 : 0);
					ct *= 2;
				}
				filterCts.add(filter);
			}
			//			System.out.println(in);
			//			System.out.println(filterCts);
			Integer noOpFilterCt = filterCts.remove((short) 0);

			Set<Short> filters = filterCts.keySet();
			vertices = new HashMap<>();
			vertices.put(in, new FilterVertex(in));
			Set<Short> lastAdded = new HashSet<>(FILTER_MAX);
			lastAdded.add(in);
			edgeCount = 0;
			while (!lastAdded.isEmpty()) {
				Set<Short> currentlyAdding = new HashSet<>(FILTER_MAX);
				for (Short v : lastAdded) {
					for (Short filter : filters) {
						short filtered = (short) (v ^ filter);
						if (!vertices.containsKey(filtered)) {
							currentlyAdding.add(filtered);
							vertices.put(filtered, new FilterVertex(filtered));
						}
						vertices.get(v).edges.put(filtered, filter);
						vertices.get(filtered).edges.put(v, filter);
					}
				}
				lastAdded = currentlyAdding;
			}
			System.out.println(vertices.size());
			long edgeCount = 0;
			for (FilterVertex vertex : vertices.values()) {
				edgeCount += vertex.edges.size();
			}
			System.out.println(edgeCount);
			if (!vertices.containsKey(MASK)) {
				System.out.println(0);
				continue;
			}

			ans = 0;
			visitedVertices = new BitSet(FILTER_MAX);
			traverse(in, 1, new BitSet());
			if (noOpFilterCt != null) {
				ans *= getPowerOf2(noOpFilterCt);
				ans %= P;
			}
			System.out.println(ans);
		}
		reader.close();
	}

	static Stack<Short> path=new Stack<>();
	static void traverse(short vertex, long curAns, BitSet appliedFilters) {
		path.push(vertex);
		if (vertex == MASK) {
			ans += curAns;
			ans %= P;
			System.out.println(appliedFilters);
			System.out.println(path);
			//			System.out.println(appliedFilters);
		}

		visitedVertices.set(vertex);
		Set<Entry<Short, Short>> edges = vertices.get(vertex).edges.entrySet();
		for (Entry<Short, Short> edge : edges) {
			Short filter = edge.getValue();
			Short oppositeVertex = edge.getKey();
			if (visitedVertices.get(oppositeVertex))
				continue;
			curAns *= filterCts.get(filter);
			curAns %= P;
			BitSet newFilters = (BitSet) appliedFilters.clone();
			newFilters.set(filter);
			traverse(edge.getKey(), curAns, newFilters);
		}
		path.pop();
	}

	static int getPowerOf2(int power) {
		char[] cs = Integer.toBinaryString(power).toCharArray();
		long ans = 1;
		int ct = 0;
		for (int i = cs.length - 1; i >= 0; i--) {
			if (cs[i] == '1') {
				ans = (ans * twoPows[ct]) % P;
			}
			ct++;
		}
		return (int) ans;
	}
}

class FilterEdge {
	short filter;

	public FilterEdge(short filter) {
		this.filter = filter;
	}

	@Override
	public String toString() {
		return filter + "";
	}

}

class FilterVertex {
	short v;
	Map<Short, Short> edges = new HashMap<>();

	public FilterVertex(short v) {
		this.v = v;
	}
}

class FilterMap extends HashMap<Short, Integer> {
	private static final long serialVersionUID = 1L;

	public FilterMap(int capacity) {
		super(capacity);
	}

	public void add(Short filter) {
		Integer ct = get(filter);
		if (ct == null)
			put(filter, 1);
		else
			put(filter, ct + 1);
	}
}