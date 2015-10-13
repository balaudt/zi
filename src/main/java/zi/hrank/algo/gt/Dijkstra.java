package zi.hrank.algo.gt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://www.hackerrank.com/challenges/dijkstrashortreach
public class Dijkstra {
	static final int P = (int) 1e8;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int ti = 0; ti < t; ti++) {
			String[] inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int m = Integer.parseInt(inStr[1]);
			int[][] w = new int[n][n];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					w[i][j] = -1;
			for (int i = 0; i < m; i++) {
				inStr = reader.readLine().split(" ");
				int x = Integer.parseInt(inStr[0]) - 1;
				int y = Integer.parseInt(inStr[1]) - 1;
				int r = Integer.parseInt(inStr[2]);
				if (w[x][y] == -1 || w[x][y] > r)
					w[x][y] = w[y][x] = r;
			}
			int s = Integer.parseInt(reader.readLine()) - 1;
			PriorityQueue<DVertex> vertices = new PriorityQueue<>(n);
			DVertex[] v = new DVertex[n];
			Map<Integer, Integer> shortestPaths = new HashMap<Integer, Integer>();
			for (int i = 0; i < n; i++) {
				DVertex vertex = new DVertex(i);
				if (i == s) {
					vertex.d = 0;
					vertex.p = -1;
				} else {
					shortestPaths.put(i, -1);
				}
				vertices.add(vertex);
				v[i] = vertex;
			}
			while (!vertices.isEmpty()) {
				DVertex u = vertices.remove();
				for (int i = 0; i < n; i++) {
					if (i == u.ind)
						continue;
					if (w[u.ind][i] != -1 && (v[i].d > u.d + w[u.ind][i])) {
						v[i].d = u.d + w[u.ind][i];
						vertices.remove(v[i]);
						vertices.add(v[i]);
					}
				}
			}
			for (int i = 0; i < v.length; i++) {
				if (i == s)
					continue;
                if (v[i].d > P)
					System.out.print("-1 ");
				else
					System.out.print(v[i].d + " ");
			}
            System.out.println();
		}
		reader.close();
	}
}

class DVertex implements Comparable<DVertex> {
	int ind, d = (int) (1e8 + 1), p;

	public DVertex(int ind) {
		this.ind = ind;
	}

	@Override
	public int compareTo(DVertex o) {
		return d - o.d;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ind;
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
		DVertex other = (DVertex) obj;
		if (ind != other.ind)
			return false;
		return true;
	}

}