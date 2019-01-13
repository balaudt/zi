package zi.chef.y15.novLong;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.uci.ics.jung.graph.DelegateForest;
import zi.common.archive.GraphUtil;

//import edu.uci.ics.jung.algorithms.layout.TreeLayout;
//import edu.uci.ics.jung.graph.DelegateForest;
//import zi.common.GraphUtil;

public class EVerifier {

	private static EVertex[] vertices;

	private static ArrayDeque<Integer> curPath;

	private static int n;

	private static BitSet coveredNodes;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/balaudt/Temp/E-gen.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			n = Integer.parseInt(inStr[0]);
			int m = Integer.parseInt(inStr[1]);
			DelegateForest<Integer, Integer> forest = new DelegateForest<>();
			vertices = new EVertex[n];
			for (int j = 0; j < n; j++) {
				vertices[j] = new EVertex(j);
				vertices[j].adjacents = new HashSet<>();
				//				forest.addVertex(j);
			}
			for (int j = 0; j < m; j++) {
				inStr = reader.readLine().split(" ");
				int v1 = Integer.parseInt(inStr[0]) - 1;
				int v2 = Integer.parseInt(inStr[1]) - 1;
				vertices[v1].adjacents.add(v2);
				vertices[v2].inCount++;
				forest.addEdge(j, v1, v2);
			}
			//			GraphUtil.printTree(forest, new TreeLayout<>(forest));
			GraphUtil.printTree(forest);

			coveredNodes = new BitSet();
			int ans = 0;
			while (true) {
				if (coveredNodes.allSet(n)) {
					System.out.println(ans);
					break;
				}
				Set<Integer> roots = new HashSet<>(n);
				boolean isMod = false;
				for (int j = 0; j < n; j++) {
					if (vertices[j] != null) {
						if (vertices[j].inCount == 0) {
							if (vertices[j].adjacents.isEmpty()) {
								coveredNodes.set(j);
								vertices[j] = null;
								isMod = true;
								continue;
							}
							roots.add(j);
						}
						vertices[j].isVisited = false;
						vertices[j].paths = new HashSet<>();
					}
				}
				if (isMod)
					continue;

				Set<BitSet> masterPaths = new HashSet<>();
				for (Integer root : roots) {
					curPath = new ArrayDeque<>();
					curPath.add(root);
					dfsVisit(root);
					masterPaths.addAll(vertices[root].paths);
				}

				for (int j = 0; j < n; j++) {
					byte ct = 0;
					BitSet requiredPath = null;
					for (BitSet path : masterPaths) {
						if (path.get(j)) {
							ct++;
							if (ct > 1)
								break;
							else
								requiredPath = path;
						}
					}
					if (ct == 1) {
						addPathToCoverSet(requiredPath);
						ans++;
						isMod = true;
					}
				}

				if (isMod)
					continue;
				//			System.out.println(coveredNodes);

				Iterator<BitSet> it = masterPaths.iterator();
				BitSet longestPath = it.next();
				int longest = longestPath.cardinality();
				while (it.hasNext()) {
					BitSet path = it.next();
					if (path.cardinality() > longest) {
						longest = path.cardinality();
						longestPath = path;
					}
				}
				addPathToCoverSet(longestPath);
				ans++;
			}
		}
	}

	static void addPathToCoverSet(BitSet path) {
		coveredNodes.or(path);
		for (int j = 0; j < n; j++) {
			if (path.get(j)) {
				if (vertices[j] == null)
					continue;
				Set<Integer> adjacents = vertices[j].adjacents;
				for (Integer adjacent : adjacents) {
					if (vertices[adjacent] != null)
						vertices[adjacent].inCount--;
				}
				vertices[j] = null;
				for (int k = 0; k < n; k++) {
					if (vertices[k] != null) {
						vertices[k].adjacents.remove(j);
					}
				}
			}
		}
	}

	static void dfsVisit(int v) {
		if (vertices[v].adjacents.isEmpty()) {
			BitSet path = new BitSet();
			path.set(v);
			ArrayDeque<Integer> currentPath = new ArrayDeque<>(curPath);
			while (!currentPath.isEmpty()) {
				Integer p = currentPath.pop();
				path.set(p);
				vertices[p].paths.add(new BitSet(path));
			}
			return;
		}
		if (vertices[v].isVisited) {
			for (BitSet path : vertices[v].paths) {
				ArrayDeque<Integer> currentPath = new ArrayDeque<>(curPath);
				while (!currentPath.isEmpty()) {
					Integer p = currentPath.pop();
					path.set(p);
					vertices[p].paths.add(new BitSet(path));
				}
			}
			return;
		}
		vertices[v].isVisited = true;
		curPath.push(v);
		for (Integer adjacent : vertices[v].adjacents) {
			dfsVisit(adjacent);
		}
		curPath.pop();
	}
}

class EVertex {
	public EVertex(int j) {
		v = j;
	}

	int v, inCount;
	Set<Integer> adjacents;
	Set<BitSet> paths;
	boolean isVisited;
}

//////////////////////////////////////
//Combination
//
//You do not need to write the code below here.
//You just need to be able to USE it.
//////////////////////////////////////

//The algorithm is from Applied Combinatorics, by Alan Tucker.
//Based on code from koders.com
