package zi.common.ds.tree;

public class DSU {
	int[] parent, rank;

	public DSU(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	public int findSet(int v) {
		if (v == parent[v]) return v;
		return parent[v] = findSet(parent[v]);
	}

	public void union(int u, int v) {
		u = findSet(u);
		v = findSet(v);
		if (u != v) {
			if (rank[u] < rank[v]) {
				int t = u;
				u = v;
				v = t;
			}
			parent[v] = u;
			rank[u] += rank[v];
		}
	}
}
