package zi.jam.y14.r1A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import edu.uci.ics.jung.graph.SparseGraph;

public class FullBinaryTreeAnalysis {
	static SparseGraph<Integer, Integer> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/2/B-large-practice.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("/home/bala/temp/2/B-large-practice.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			graph = new SparseGraph<Integer, Integer>();
			for (int j = 0; j < n; j++) {
				graph.addVertex(j + 1);
			}
			for (int j = 0; j < n - 1; j++) {
				String[] str = reader.readLine().split(" ");
				graph.addEdge(j + 1, Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			}
			int minDeletions = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				int deletions = n - getMaxUndeletion(j + 1, -1);
				if (deletions < minDeletions)
					minDeletions = deletions;
			}
			StringBuilder builder = new StringBuilder("Case #").append(i + 1).append(": ").append(minDeletions).append('\n');
			writer.write(builder.toString());
			System.out.print(builder.toString());
		}
		reader.close();
		writer.close();
	}

	static int getMaxUndeletion(int vertex, Integer parent) {
		Collection<Integer> neighbors = new ArrayList<Integer>(graph.getNeighbors(vertex));
		neighbors.remove(parent);
		if (neighbors.size() <= 1)
			return 1;
		int[] maxChildren = new int[neighbors.size()];
		int count = 0;
		for (Integer neighbor : neighbors) {
			maxChildren[count++] = getMaxUndeletion(neighbor, vertex);
		}
		Arrays.sort(maxChildren);
		return 1 + maxChildren[maxChildren.length - 2] + maxChildren[maxChildren.length - 1];
	}
}
