package zi.hrank.algo.gt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BellmanFord {

	public static int P = (int) 1e8;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int ti = 0; ti < t; ti++) {
			String[] inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int m = Integer.parseInt(inStr[1]);
			List<List<Edge>> edges = new ArrayList<>(n);
			for (int i = 0; i < n; i++) {
				edges.add(new ArrayList<>());
			}
			for (int i = 0; i < m; i++) {
				inStr = reader.readLine().split(" ");
				int x = Integer.parseInt(inStr[0]) - 1;
				int y = Integer.parseInt(inStr[1]) - 1;
				int r = Integer.parseInt(inStr[2]);
				Edge edge = new Edge(y, r);
				List<Edge> currentEdges = edges.get(x);
				if (!currentEdges.contains(edge)) {
					currentEdges.add(edge);
					edges.get(y).add(new Edge(x, r));
				} else {
					edge = currentEdges.get(currentEdges.indexOf(edge));
					if (edge.w > r) {
						edge.w = r;
						Edge anotherEdge = edges.get(y).get(edges.get(y).indexOf(new Edge(x, r)));
						anotherEdge.w = r;
					}
				}
			}
			int s = Integer.parseInt(reader.readLine()) - 1;
			BFVertex[] vs = new BFVertex[n];
			for (int i = 0; i < n; i++) {
				vs[i] = new BFVertex();
				if (i == s)
					vs[i].d = 0;
			}
			for (int i = 0; i < n; i++) {
				List<Edge> currentEdges = edges.get(i);
				BFVertex u = vs[i];
				for (Edge edge : currentEdges) {
					BFVertex v = vs[edge.d];
					int w = edge.w;
					if (v.d > u.d + w) {
						v.d = u.d + w;
						v.p = i;
					}
				}
			}
			for (int i = 0; i < n; i++) {
				if (i == s)
					continue;
				if (vs[i].d > P)
					System.out.print("-1 ");
				else
					System.out.print(vs[i].d + " ");
			}
		}
		reader.close();
	}
}

class Edge {
	int d, w;

	public Edge(int d, int w) {
		this.d = d;
		this.w = w;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + d;
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
		Edge other = (Edge) obj;
		if (d != other.d)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "E[d=" + d + ", w=" + w + "]";
	}

}

class BFVertex {
	//As adding value to d results in negative no, limiting d to be nearest power of 10 greater than max(|V|*w)=3000*350
	int p = -1, d = BellmanFord.P + 1;

	@Override
	public String toString() {
		return "V[p=" + p + ", d=" + d + "]";
	}

}