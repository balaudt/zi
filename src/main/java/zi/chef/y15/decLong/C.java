package zi.chef.y15.decLong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class C {
	static int n, m;
	static char[][] in;
	static Set<Node> leaves;

	public static void main(String[] args) throws Exception {
		//		long time = System.currentTimeMillis();
//		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/dec/C-sin.in"));
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			n = Integer.parseInt(reader.readLine());
			in = new char[n][];
			for (int j = 0; j < n; j++) {
				in[j] = reader.readLine().toCharArray();
			}
			m = in[0].length;
			/*if (t > 14 || m > 14 || n > 14) {
				reader.close();
				throw new UnsupportedOperationException();
			}*/
			HashSet<String> commonSeqs = getAllCommonSubsequences();
			Set<String> lastSeqs = new HashSet<String>();
			lastSeqs.add("");
			int firIter = 0;
			for (int j = 0; j < m; j++) {
				LCSMap lcsMap = append(commonSeqs, lastSeqs);
				Entry<Integer, Set<String>> entry = lcsMap.entrySet().iterator().next();
				firIter = entry.getKey();
				lastSeqs = entry.getValue();
//				System.out.println(lastSeqs);
			}
			lastSeqs = new HashSet<>();
			lastSeqs.add("");
			int ans = 0;
			for (int j = 0; j < m; j++) {
				LCSMap lcsMap = append(commonSeqs, lastSeqs);
				Set<String> nextSeqs = new HashSet<>();
				Set<Entry<Integer, Set<String>>> entries = lcsMap.entrySet();
				for (Entry<Integer, Set<String>> entry : entries) {
					if (entry.getKey() <= firIter)
						nextSeqs.addAll(entry.getValue());
				}
				lastSeqs = nextSeqs;
				ans = lcsMap.entrySet().iterator().next().getKey();
//				System.out.println(lastSeqs);
			}
			System.out.println(ans);
		}
		//				System.out.println(System.currentTimeMillis() - time);
		reader.close();
	}

	static LCSMap append(Set<String> commonSeqs, Set<String> perms) {
		LCSMap lcsMap = new LCSMap();
		int j = perms.iterator().next().length();
		for (int k = 0; k < 2; k++) {
			for (String lastSeq : perms) {
				lastSeq = new StringBuilder(lastSeq).append((char) ('a' + k)).toString();
				int maxLcs = Integer.MIN_VALUE;
				for (String commonSeq : commonSeqs) {
					int lcsLen = lcsLen(lastSeq, commonSeq);
					if (lcsLen > maxLcs)
						maxLcs = lcsLen;
				}
				lcsMap.put(maxLcs, lastSeq);

				lastSeq = new StringBuilder(lastSeq).deleteCharAt(j).insert(0, (char) ('a' + k)).toString();
				maxLcs = Integer.MIN_VALUE;
				for (String commonSeq : commonSeqs) {
					int lcsLen = lcsLen(lastSeq, commonSeq);
					if (lcsLen > maxLcs)
						maxLcs = lcsLen;
				}
				lcsMap.put(maxLcs, lastSeq);
			}
		}
		return lcsMap;
	}

	static HashSet<String> getAllCommonSubsequences() {
		int indices[] = new int[n];
		for (int i = 0; i < indices.length; i++) {
			indices[i] = -1;
		}
		Node root = new Node(' ', indices);
		leaves = new HashSet<>();
		traverse(root);
		HashSet<String> commonSubsequences = new HashSet<>();
		for (Node leaf : leaves) {
			char seq[] = new char[m];
			int ct = 0;
			while (leaf.c != ' ') {
				seq[ct++] = leaf.c;
				leaf = leaf.parent;
			}
			commonSubsequences.add(reverse(new String(seq).trim()));
		}
		return commonSubsequences;
	}

	static String reverse(String str) {
		char[] cs = str.toCharArray();
		for (int i = 0; i < cs.length / 2; i++) {
			char t = cs[cs.length - i - 1];
			cs[cs.length - i - 1] = cs[i];
			cs[i] = t;
		}
		return new String(cs);
	}

	static void traverse(Node v) {
		boolean isLeaf = true;
		for (int i = 0; i < 2; i++) {
			boolean isFeasible = true;
			int[] newIndices = new int[n];
			for (int j = 0; j < n; j++) {
				int lastInd = v.indices[j];
				while (++lastInd < m && in[j][lastInd] != 'a' + i)
					;
				if (lastInd == m) {
					isFeasible = false;
					break;
				}
				newIndices[j] = lastInd;
			}
			if (isFeasible) {
				Node child = new Node((char) ('a' + i), newIndices);
				v.addChild(child);
				traverse(child);
				isLeaf = false;
			}
		}
		if (isLeaf)
			leaves.add(v);
	}

	static int lcsLen(String x, String y) {
		int M = x.length();
		int N = y.length();

		// opt[i][j] = length of LCS of x[i..M] and y[j..N]
		int[][] opt = new int[M + 1][N + 1];

		// compute length of LCS and all subproblems via dynamic programming
		for (int i = M - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (x.charAt(i) == y.charAt(j))
					opt[i][j] = opt[i + 1][j + 1] + 1;
				else
					opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
			}
		}

		return opt[0][0];
	}
}

class Node {
	char c;
	int[] indices;
	Node children[] = new Node[2];
	Node parent;

	public Node() {
	}

	public Node(char c, int[] indices) {
		this.c = c;
		this.indices = indices;
	}

	void addChild(Node c) {
		c.parent = this;
		if (children[0] == null)
			children[0] = c;
		else
			children[1] = c;
	}
}

class LCSMap extends TreeMap<Integer, Set<String>> {

	private static final long serialVersionUID = 1L;

	public void put(Integer key, String value) {
		Set<String> list = get(key);
		if (list == null) {
			list = new HashSet<>();
			put(key, list);
		}
		list.add(value);
	}

}