package zi.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author balamurugan
 */
public class TopologySort {
	class Vertex {
		int ind;
		char color = 'W';
		List<Integer> adjList = new ArrayList<>();
		int d, f;

		Vertex(int ind) {
			this.ind = ind;
		}
	}

	private Vertex[] v;
	private int t = 0;

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		v = new Vertex[numCourses];
		for (int i = 0; i < numCourses; i++)
			v[i] = new Vertex(i);
		for (int[] prerequisite : prerequisites) {
			v[prerequisite[0]].adjList.add(prerequisite[1]);
		}
		for (int i = 0; i < numCourses; i++)
			if (!dfs(v[i]))
				return new int[0];
		Arrays.sort(v, Comparator.comparingInt(v -> v.f));
		int[] out = new int[numCourses];
		for (int i = 0; i < numCourses; i++)
			out[i] = v[i].ind;
		return out;
	}

	private boolean dfs(Vertex u) {
		if (u.color == 'B')
			return true;
		if (u.color == 'G')
			return false;
		u.color = 'G';
		u.d = t++;
		for (int nei : u.adjList)
			if (!dfs(v[nei]))
				return false;
		u.f = t++;
		u.color = 'B';
		return true;
	}
}
