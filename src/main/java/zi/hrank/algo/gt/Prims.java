package zi.hrank.algo.gt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.PriorityQueue;

//https://www.hackerrank.com/challenges/primsmstsub
public class Prims {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int m = Integer.parseInt(inStr[1]);
		int[][] w = new int[n][n];
		for (int i = 0; i < m; i++) {
			inStr = reader.readLine().split(" ");
			int x = Integer.parseInt(inStr[0]) - 1;
			int y = Integer.parseInt(inStr[1]) - 1;
			int r = Integer.parseInt(inStr[2]);
			if (w[x][y] == 0 || w[x][y] > r)
				w[x][y] = w[y][x] = r;
		}
		int s = Integer.parseInt(reader.readLine()) - 1;
		PriorityQueue<Vertex> vertices = new PriorityQueue<>(n);
		Vertex[] v = new Vertex[n];
		BitSet isInA = new BitSet(n);
		for (int i = 0; i < n; i++) {
			Vertex vertex = new Vertex(i);
			if (i == s) {
				vertex.key = 0;
				isInA.set(i);
			}
			vertices.add(vertex);
			v[i] = vertex;
		}
		int totalWeight = 0;
		while (!vertices.isEmpty()) {
			Vertex u = vertices.remove();
			isInA.set(u.ind);
			totalWeight += u.key;
			for (int i = 0; i < n; i++) {
				if (i == u.ind)
					continue;
				if (!isInA.get(i) && w[u.ind][i] != 0 && w[u.ind][i] < v[i].key) {
					v[i].key = w[u.ind][i];
					//The following two lines are to re-trigger heapify on i in Q
					vertices.remove(v[i]);
					vertices.add(v[i]);
				}
			}
		}
		System.out.println(totalWeight);
		reader.close();
	}
}

class Vertex implements Comparable<Vertex> {
	int ind;
	int key = Integer.MAX_VALUE;

	public Vertex(int ind) {
		this.ind = ind;
	}

	@Override
	public int compareTo(Vertex o) {
		return key - o.key;
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
		Vertex other = (Vertex) obj;
		if (ind != other.ind)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "V[i=" + ind + ", k=" + key + "]";
	}

}