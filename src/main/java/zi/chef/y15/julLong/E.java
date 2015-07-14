package zi.chef.y15.julLong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

public class E {
	static final String FOLDER = "/home/bala/temp/8/";
	static final int P = (int) (1e9 + 7);

	public static void main(String[] args) throws Exception {
		long ct = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "E-gen.in"));
		System.setOut(new PrintStream(FOLDER + "E-gen.out"));
		//						BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int q = Integer.parseInt(inStr[1]);
		inStr = reader.readLine().split(" ");
		Node[] a = new Node[n];
		for (int i = 0; i < n; i++) {
			a[i] = new Node(Integer.parseInt(inStr[i]));
		}
		ESegmentTree tree = new ESegmentTree(Node.class, a);
		for (int i = 0; i < q; i++) {
			inStr = reader.readLine().split(" ");
			int op = Integer.parseInt(inStr[0]);
			int l = Integer.parseInt(inStr[1]) - 1;
			int r = Integer.parseInt(inStr[2]) - 1;
			if (op < 4) {
				int v = Integer.parseInt(inStr[3]);
				tree.storeLazyQuery(new Query(op, i, v), l, r);
			} else {
				System.out.println(tree.query(l, r, i));
				//				tree.query(l, r);
			}
		}
		//		System.arraycopy(tree.nodes, tree.maxsize, a, 0, n);
		//		System.out.println(Arrays.toString(a));
		System.out.println(System.currentTimeMillis() - ct);
		reader.close();
	}

}

class Query implements Comparable<Query> {
	int index, v;
	BitSet op = new BitSet(2);

	public Query(int oper, int index, int v) {
		this.index = index;
		this.v = v;
		if (oper == 1)
			op.set(0, true);
		else if (oper == 2)
			op.set(1, true);
	}

	public Query(int index) {
		this.index = index;
	}

	int getOp() {
		if (op.get(0))
			return 1;
		else if (op.get(1))
			return 2;
		else
			return 3;
	}

	@Override
	public int compareTo(Query q2) {
		return index - q2.index;
	}

	@Override
	public String toString() {
		return "Q[i=" + index + ",v=" + v + ",o=" + getOp() + "]";
	}
}

class Node {
	int sum;
	List<Query> queries;
	boolean isDirty;
	int lastQueryCleaned;

	public Node(int sum) {
		this(sum, null);
	}

	public Node(int sum, List<Query> dirtyQueries) {
		this.sum = sum;
		this.queries = dirtyQueries;
	}

	@Override
	public String toString() {
		return "N [s=" + sum + ",q=" + queries + ",d=" + (isDirty ? '1' : '0') + ",lq=" + lastQueryCleaned + "]";
	}

}

class ESegmentTree extends SegmentTree<Node> {
	static final int P = (int) (1e9 + 7);

	public ESegmentTree(Class<Node> clazz, Node[] leaves) throws Exception {
		super(clazz, leaves);
	}

	@Override
	protected Node initialize(int ind) throws Exception {
		if (ind >= maxsize)
			return nodes[ind];
		return new Node(0);
	}

	@Override
	protected Node merge(Node leftNode, Node rightNode, Node parentNode) {
		try {
			int left = leftNode.sum;
			long right = rightNode.sum;
			parentNode.sum = (int) ((left + right) % P);
			return parentNode;
		} catch (NullPointerException e) {
			if (parentNode == null || (leftNode == null && rightNode == null))
				return parentNode;
			parentNode.sum = leftNode.sum;
			return parentNode;
		}
	}

	void storeLazyQuery(Query query, int l, int r) {
		storeLazyQuery(query, l, r, 0, maxsize, 0);
	}

	private void storeLazyQuery(Query query, int l, int r, int nl, int nr, int nind) {
		if (l > nr || r < nl)
			return;
		Node node = nodes[nind];
		node.isDirty = true;
		if (l <= nl && r >= nr) {
			if (node.queries == null)
				node.queries = new ArrayList<>();
			if (query.getOp() == 3)
				node.queries.clear();
			node.queries.add(query);
			return;
		}
		int mid = mid(nl, nr);
		storeLazyQuery(query, l, r, nl, mid, leftchild(nind));
		storeLazyQuery(query, l, r, mid + 1, nr, rightchild(nind));
	}

	int query(int l, int r, int queryIndex) {
		ArrayList<Query> ancestorQueries = new ArrayList<Query>();
		return query(l, r, 0, maxsize, 0, ancestorQueries);
	}

	int query(int l, int r, int nl, int nr, int nind, List<Query> ancestorQueries) {
		if (l > nr || r < nl)
			return 0;
		Node node = nodes[nind];
		//Get the sum directly without process if !node.isDirty && ancestorQueries.isEmpty
		if (!node.isDirty && ancestorQueries.isEmpty()) {
			return queryWithOutProcess(l, r, nl, nr, nind);
		}

		//Merge current query list to ancestor queries maintaining ordering by query index to allow binarySearch
		//New list creation to allow fork-join
		if (node.queries != null && !node.queries.isEmpty()) {
			if (ancestorQueries.isEmpty())
				ancestorQueries = node.queries;
			else
				ancestorQueries = mergeQueries(ancestorQueries, node.queries, node.lastQueryCleaned);
		}

		//If all ancestor queries were already processed
		if (!node.isDirty && ancestorQueries.isEmpty()) {
			return queryWithOutProcess(l, r, nl, nr, nind);
		}

		//Do the actual process in case of a single element range
		if (nl == nr) {
			int size = ancestorQueries.size();
			if (size == 0)
				return node.sum;
			for (int i = 0; i < size; i++) {
				//TODO Optimization possible here to skip operations prior to last op type 3
				Query query = ancestorQueries.get(i);
				if (query.getOp() == 3)
					node.sum = query.v;
				else if (query.getOp() == 2)
					node.sum = (int) (((long) query.v * node.sum) % P);
				else
					node.sum = (int) (((long) query.v + node.sum) % P);
			}
			node.lastQueryCleaned = ancestorQueries.get(size - 1).index;
			node.isDirty = false;
			if (node.queries != null) {
				node.queries.clear();
			}
			return node.sum;
		}

		//Process the children and return either the partial or full sum; clear dirty flag and queries in case of full sum
		int mid = mid(nl, nr);
		int leftchild = leftchild(nind);
		int leftSum = query(l, r, nl, mid, leftchild, ancestorQueries);
		int rightchild = rightchild(nind);
		int rightSum = query(l, r, mid + 1, nr, rightchild, ancestorQueries);
		if (l <= nl && r >= nr) {
			merge(nodes[leftchild], nodes[rightchild], nodes[nind]);
			node.isDirty = false;
			//TODO Store the lastQueryCleaned only in the most common ancestor to allow less memory consumption
			if (!ancestorQueries.isEmpty()) {
				node.lastQueryCleaned = ancestorQueries.get(ancestorQueries.size() - 1).index;
			}
			if (node.queries != null) {
				node.queries.clear();
			}
			return node.sum;
		} else {
			return (int) (((long) leftSum + rightSum) % P);
		}
	}

	private int queryWithOutProcess(int l, int r, int nl, int nr, int ind) {
		if (l > nr || r < nl)
			return 0;
		if (nl == nr) {
			return nodes[ind].sum;
		}
		if (l <= nl && r >= nr) {
			return nodes[ind].sum;
		}
		int mid = mid(nl, nr);
		int left = queryWithOutProcess(l, r, nl, mid, leftchild(ind));
		int right = queryWithOutProcess(l, r, mid + 1, nr, rightchild(ind));
		return (int) (((long) left + right) % P);
	}

	private List<Query> mergeQueries(List<Query> ancestorQueries, List<Query> nodeQueries, int lastQueryProcessed) {
		int ancesSize = ancestorQueries.size();
		int curSize = nodeQueries.size();
		int ancPt = Collections.binarySearch(ancestorQueries, new Query(lastQueryProcessed));
		if (ancPt > 0)
			ancPt++;
		else {
			ancPt = -ancPt - 1;
			if (ancPt < 0)
				ancPt++;
		}
		List<Query> res = new ArrayList<>(curSize + (ancesSize - ancPt));
		int curPt = 0;
		while (ancPt < ancesSize && curPt < curSize) {
			if (ancestorQueries.get(ancPt).index < nodeQueries.get(curPt).index) {
				res.add(ancestorQueries.get(ancPt));
				ancPt++;
			} else {
				res.add(nodeQueries.get(curPt));
				curPt++;
			}
		}
		while (ancPt < ancesSize) {
			res.add(ancestorQueries.get(ancPt));
			ancPt++;
		}
		while (curPt < curSize) {
			res.add(nodeQueries.get(curPt));
			curPt++;
		}
		if (!ancestorQueries.isEmpty() && !nodeQueries.isEmpty() && lastQueryProcessed > 0) {
			System.out.println(ancestorQueries + "\t" + nodeQueries + "\t" + lastQueryProcessed + "\t" + res);
		}
		return res;
	}
}

abstract class SegmentTree<N> {
	public N[] getNodes() {
		return nodes;
	}

	protected N[] nodes;
	protected int maxsize;
	protected Class<N> clazz;

	protected int leftchild(int pos) {
		return 2 * pos + 1;
	}

	protected int rightchild(int pos) {
		return 2 * pos + 2;
	}

	protected int mid(int start, int end) {
		return (start + (end - start) / 2);
	}

	@SuppressWarnings("unchecked")
	public SegmentTree(Class<N> clazz, int size) throws Exception {
		this.clazz = clazz;
		int height = (int) (Math.ceil(Math.log(size) / Math.log(2)));
		maxsize = (int) Math.pow(2, height + 1);
		nodes = (N[]) Array.newInstance(clazz, maxsize + 1);
		maxsize /= 2;
		maxsize--;
	}

	public SegmentTree(Class<N> clazz, N[] leaves) throws Exception {
		this(clazz, leaves.length);
		System.arraycopy(leaves, 0, nodes, maxsize, leaves.length);
		initialize(0, maxsize, 0);
	}

	protected N initialize(int l, int r, int ind) throws Exception {
		if (l == r) {
			return initialize(ind);
		}
		N thisNode = initialize(ind);
		int mid = mid(l, r);
		N leftNode = initialize(l, mid, leftchild(ind));
		N rightNode = initialize(mid + 1, r, rightchild(ind));
		nodes[ind] = merge(leftNode, rightNode, thisNode);
		return nodes[ind];
	}

	abstract protected N merge(N leftNode, N rightNode, N parentNode);

	protected N initialize(int ind) throws Exception {
		return clazz.newInstance();
	}

}
