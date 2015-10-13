package zi.hrank.algo.gt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//https://www.hackerrank.com/challenges/bfsshortreach

public class BFS {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int m = Integer.parseInt(inStr[1]);
			Node[] nodes = new Node[n];
			for (int j = 0; j < n; j++) {
				nodes[j] = new Node(n);
			}
			for (int j = 0; j < m; j++) {
				inStr = reader.readLine().split(" ");
				int s = Integer.parseInt(inStr[0]) - 1;
				int d = Integer.parseInt(inStr[1]) - 1;
				nodes[s].adjMat[d] = nodes[d].adjMat[s] = true;
			}
			int s = Integer.parseInt(reader.readLine()) - 1;
			nodes[s].color = 1;
			nodes[s].d = 0;
			Queue<Integer> reachableNodes = new ArrayDeque<>();
			reachableNodes.add(s);
			while (!reachableNodes.isEmpty()) {
				Integer u = reachableNodes.remove();
				for (int j = 0; j < n; j++) {
					if (nodes[u].adjMat[j] && nodes[j].color == 0) {
						nodes[j].color = 1;
						nodes[j].d = nodes[u].d + 6;
						nodes[j].predecessor = u;
						reachableNodes.add(j);
					}
				}
				nodes[u].color = 2;
			}
			for (int j = 0; j < n; j++) {
				if (j == s)
					continue;
				System.out.print(nodes[j].d + " ");
			}
			System.out.println();
		}
		reader.close();
	}
}

class Node {
	short color;
	int d = -1;
	int predecessor;
	boolean adjMat[];

	public Node(int vsize) {
		adjMat = new boolean[vsize];
	}
}