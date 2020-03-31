package zi.leet.google;

import java.util.HashSet;
import java.util.Set;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/letter-tile-possibilities/
public class Tiles {
	private char[] in;
	private Set<String> allVisited;

	public int numTilePossibilities(String tiles) {
		in = tiles.toCharArray();
		allVisited = new HashSet<>();
		int n = in.length;
		for (int i = 1; i <= n; i++) {
			char[] out = new char[i];
			boolean[] visited = new boolean[n];
			permute(out, 0, visited);
		}
		return allVisited.size();
	}

	private void permute(char[] out, int st, boolean[] visited) {
		if (st == out.length) {
			allVisited.add(new String(out));
			return;
		}
		for (int i = 0; i < in.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				out[st] = in[i];
				permute(out, st + 1, visited);
				visited[i] = false;
			}
		}
	}
}
