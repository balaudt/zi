package zi.temp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import zi.common.SegmentTree;

public class EAct {
	static final String FOLDER = "C:/ft/37/";
	static final int P = (int) (1e9 + 7);

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "E-sample.in"));
		//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
//				tree.draw();
			} else {
				System.out.println(tree.query(l, r, i));
			}
		}
		reader.close();
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
		int left = leftNode.sum;
		long right = rightNode.sum;
		parentNode.sum = (int) ((left + right) % P);
		return parentNode;
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

	int query(int l, int r, int nl, int nr, int nind, ArrayList<Query> ancestorQueries) {
		if (l > nr || r < nl)
			return 0;
		Node node = nodes[nind];
		if (!node.isDirty && ancestorQueries.isEmpty()) {
			return node.sum;
		}

		//Add current query list to ancestor queries
		if (node.queries != null)
			ancestorQueries.addAll(node.queries);
		Collections.sort(ancestorQueries);

		int size = ancestorQueries.size();
		int queryStart = Collections.binarySearch(ancestorQueries, new Query(node.lastQueryCleaned));
		if (queryStart > 0)
			queryStart++;
		else
			queryStart = -queryStart - 1;
		

		//Do the actual process in case of a single element range
		if (nl == nr) {
			for (int i = queryStart; i < size; i++) {
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
				ancestorQueries.removeAll(node.queries);
			}
			return node.sum;
		}

		//Get the sum directly without process if queryStart>=size && !node.isDirty && ancestorQueries.isEmpty
		
		//Process the children and return either the partial or full sum; clear dirty flag and queries in case of full sum
		int mid = mid(nl, nr);
		int leftchild = leftchild(nind);
		int leftSum = query(l, r, nl, mid, leftchild, ancestorQueries);
		int rightchild = rightchild(nind);
		int rightSum = query(l, r, mid + 1, nr, rightchild, ancestorQueries);
		if (l <= nl && r >= nr) {
			merge(nodes[leftchild], nodes[rightchild], nodes[nind]);
			node.isDirty = false;
			if (node.queries != null) {
				node.lastQueryCleaned = ancestorQueries.get(ancestorQueries.size() - 1).index;
				ancestorQueries.removeAll(node.queries);
				node.queries.clear();
			}
			return node.sum;
		} else {
			return leftSum + rightSum;
		}
	}
}