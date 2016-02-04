package zi.chef.y15.novLong;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

//import edu.uci.ics.jung.graph.DelegateForest;
//import zi.common.GraphUtil;

//import edu.uci.ics.jung.algorithms.layout.TreeLayout;
//import edu.uci.ics.jung.graph.DelegateForest;
//import zi.common.GraphUtil;

public class E {

	private static Vertex[] vertices;

	private static ArrayDeque<Integer> curPath;

	private static int n;

	private static BitSet coveredNodes;

	private static Comparator<BitSet> greedyComp = new Comparator<BitSet>() {

		public int compare(BitSet l, BitSet r) {
			int diff = splits(masterPaths, l) - splits(masterPaths, r);
			if (diff != 0)
				return diff;
			return r.cardinality() - l.cardinality();
		}

		public int compare2(BitSet l, BitSet r) {
			int diff = r.cardinality() - l.cardinality();
			if (diff != 0)
				return diff;
			return splits(masterPaths, l) - splits(masterPaths, r);
		}

	};

	private static Set<BitSet> masterPaths;

	private static Set<BitSet> requiredPaths;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/balaudt/Temp/E-gen-1.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			n = Integer.parseInt(inStr[0]);
			int m = Integer.parseInt(inStr[1]);
			//			DelegateForest<Integer, Integer> forest = new DelegateForest<>();
			vertices = new Vertex[n];
			for (int j = 0; j < n; j++) {
				vertices[j] = new Vertex(j);
				vertices[j].adjacents = new HashSet<>();
				//				forest.addVertex(j);
			}
			for (int j = 0; j < m; j++) {
				inStr = reader.readLine().split(" ");
				int v1 = Integer.parseInt(inStr[0]) - 1;
				int v2 = Integer.parseInt(inStr[1]) - 1;
				vertices[v1].adjacents.add(v2);
				vertices[v2].inCount++;
				//				forest.addEdge(j, v1, v2);
			}
			//			GraphUtil.printTree(forest, new TreeLayout<>(forest));
			//			GraphUtil.printTree(forest);

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
								//																System.out.println("**" + j);
								coveredNodes.set(j);
								vertices[j] = null;
								isMod = true;
								ans++;
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

				masterPaths = new HashSet<>();
				for (Integer root : roots) {
					curPath = new ArrayDeque<>();
					curPath.add(root);
					dfsVisit(root);
					masterPaths.addAll(vertices[root].paths);
				}

				requiredPaths = new HashSet<>();
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
						requiredPaths.add(requiredPath);
					}
				}

				//			System.out.println(coveredNodes);

				ArrayList<BitSet> sortedPaths = new ArrayList<>(masterPaths);
				Collections.sort(sortedPaths, greedyComp);
				addPathToCoverSet(sortedPaths.get(0));
				ans++;
			}
		}
	}

	private static int splits(Set<BitSet> masterPaths, BitSet longestPath) {
		int longestSplit = 0;
		for (BitSet path : masterPaths) {
			if (path == longestPath || requiredPaths.contains(path))
				continue;
			if ((path.words[0] & longestPath.words[0]) > 0 || (path.words[1] & longestPath.words[1]) > 0)
				longestSplit++;
		}
		return longestSplit;
	}

	static void addPathToCoverSet(BitSet path) {
		//		System.out.println(path.toFullString());
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

class Vertex {
	public Vertex(int j) {
		v = j;
	}

	int v, inCount;
	Set<Integer> adjacents;
	Set<BitSet> paths;
	boolean isVisited;
}

class BitSet implements Comparable<BitSet> {
	long words[] = new long[2];
	private final static int ADDRESS_BITS_PER_WORD = 6;

	public BitSet() {
	}

	public BitSet(BitSet other) {
		words[0] = other.words[0];
		words[1] = other.words[1];
	}

	private static int wordIndex(int bitIndex) {
		return bitIndex >> ADDRESS_BITS_PER_WORD;
	}

	void set(int bitIndex) {
		words[wordIndex(bitIndex)] |= (1L << bitIndex);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(words);
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
		BitSet other = (BitSet) obj;
		if (!Arrays.equals(words, other.words))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Long.toBinaryString(words[0]);
	}

	public boolean get(int bitIndex) {
		return (words[wordIndex(bitIndex)] & (1L << bitIndex)) != 0;
	}

	public void or(BitSet other) {
		words[0] |= other.words[0];
		words[1] |= other.words[1];
	}

	public void andNew(BitSet other) {
		BitSet out = new BitSet();
		out.words[0] = words[0] & other.words[0];
		out.words[1] = words[1] & other.words[1];
	}

	private static final long WORD_MASK = 0xffffffffffffffffL;

	public boolean allSet(int n) {
		byte wi = 0;
		if (wordIndex(n) == 1) {
			if (words[0] != WORD_MASK)
				return false;
			wi = 1;
		}
		long mask = (1L << n);
		mask--;
		return mask == words[wi];
	}

	@Override
	public int compareTo(BitSet o) {
		return cardinality() - o.cardinality();
	}

	public int cardinality() {
		return Long.bitCount(words[0]) + Long.bitCount(words[1]);
	}

	public String toFullString() {
		StringBuilder builder = new StringBuilder("[");
		for (int i = 0; i < 2; i++) {
			long word = words[i];
			int ct = 0;
			while (word != 0) {
				if ((word & 1L) == 1)
					builder.append(ct).append(',');
				word >>= 1;
				ct++;
			}
		}
		if (builder.length() > 1)
			builder.deleteCharAt(builder.length() - 1);
		builder.append(']');
		return builder.toString();
	}
}

//////////////////////////////////////
//Combination
//
//You do not need to write the code below here.
//You just need to be able to USE it.
//////////////////////////////////////

//The algorithm is from Applied Combinatorics, by Alan Tucker.
//Based on code from koders.com

class Combination {
	private int n, r;
	private int[] index;
	private boolean hasNext = true;

	public Combination(int n, int r) {
		this.n = n;
		this.r = r;
		index = new int[r];
		for (int i = 0; i < r; i++)
			index[i] = i;
	}

	public boolean hasNext() {
		return hasNext;
	}

	// Based on code from KodersCode:
	// The algorithm is from Applied Combinatorics, by Alan Tucker.
	// Move the index forward a notch. The algorithm finds the rightmost
	// index element that can be incremented, increments it, and then 
	// changes the elements to the right to each be 1 plus the element on their left. 
	//
	// For example, if an index of 5 things taken 3 at a time is at {0 3 4}, only the 0 can
	// be incremented without running out of room. The next index is {1, 1+1, 1+2) or
	// {1, 2, 3}. This will be followed by {1, 2, 4}, {1, 3, 4}, and {2, 3, 4}.

	private void moveIndex() {
		int i = rightmostIndexBelowMax();
		if (i >= 0) {
			index[i] = index[i] + 1;
			for (int j = i + 1; j < r; j++)
				index[j] = index[j - 1] + 1;
		} else
			hasNext = false;
	}

	public int[] next() {
		if (!hasNext)
			return null;
		int[] result = new int[r];
		for (int i = 0; i < r; i++)
			result[i] = index[i];
		moveIndex();
		return result;
	}

	// return int,the index which can be bumped up.
	private int rightmostIndexBelowMax() {
		for (int i = r - 1; i >= 0; i--)
			if (index[i] < n - r + i)
				return i;
		return -1;
	}

}