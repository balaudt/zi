package zi.hrank.algo.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

//https://www.hackerrank.com/challenges/connected-cell-in-a-grid
public class ConnectedCell {
	static int n, m;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(reader.readLine());
		m = Integer.parseInt(reader.readLine());
		Node[] nodes = new Node[n * m];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(i);
		}
		for (int i = 0; i < n; i++) {
			char[] in = reader.readLine().replaceAll(" ", "").toCharArray();
			for (int j = 0; j < m; j++) {
				if (in[j] == '1') {
					int ind = m * i + j;
					nodes[ind].flag = true;
					ArrayList<Integer> indicesToCheck = indicesToCheck(i, j);
					for (Integer ic : indicesToCheck) {
						if (nodes[ic].flag) {
							nodes[ind].root = ic;
							while (nodes[ind].root != ind) {
								ind = nodes[ind].root;
							}
							nodes[ind].count++;
							break;
						}
					}
				}
			}
		}
		int maxCon = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (nodes[i * m + j].count > maxCon)
					maxCon = nodes[i * m + j].count;
			}
		}
		System.out.println(maxCon);
		reader.close();
	}

	static ArrayList<Integer> indicesToCheck(int r, int c) {
		int[][] indicesToCheck = new int[][] { { r - 1, c - 1 }, { r - 1, c }, { r - 1, c + 1 }, { r, c - 1 } };
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < indicesToCheck.length; i++) {
			int[] in = indicesToCheck[i];
			if (in[0] >= 0 && in[0] < n && in[1] >= 0 && in[1] < m)
				indices.add(in[0] * m + in[1]);
		}
		return indices;
	}
}

class Node {
	int root, count;
	boolean flag = false;

	public Node(int root) {
		this.root = root;
		count = 1;
	}
}