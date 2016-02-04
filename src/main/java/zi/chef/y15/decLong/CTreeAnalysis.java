package zi.chef.y15.decLong;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;

import edu.uci.ics.jung.graph.DelegateTree;

public class CTreeAnalysis {

	static int vertexCounter = 0;
	static char[][] strs;
	static int k = 14, n = 14;
	static DelegateTree<LCSVertex, Integer> tree = new DelegateTree<>();
	static int edgeCounter = 0;

	public static void main(String[] args) {
		strs = new char[k][];
		for (int i = 0; i < k; i++) {
			strs[i] = RandomStringUtils.random(n, "ab").toCharArray();
			//			System.out.println(strs[i]);
		}

		long time = System.currentTimeMillis();
		//		HashSet<String> rootLcs = findLcs(false);
		//		System.out.println("LCS set of input strings\t" + rootLcs);

		HashSet<String> commonSeqs = findLcs(true);

		int maxVal = (int) Math.pow(2, n);
		/*char[][] backStr = strs;
		k++;
		char[][] newStr = new char[k][n];
		for (int i = 0; i < backStr.length; i++) {
			newStr[i] = backStr[i];
		}
		strs = newStr;
		int minLen = Integer.MAX_VALUE;
		HashSet<String> minStrs = null;
		for (int i = 0; i < maxVal; i++) {
			StrBuilder builder = new StrBuilder(
					StringUtils.reverse(Integer.toBinaryString(i).replaceAll("0", "a").replaceAll("1", "b")));
			while (builder.length() != n) {
				builder.append('a');
			}
			newStr[k - 1] = builder.reverse().toCharArray();
			HashSet<String> lcses = findLcs(false);
			if (!lcses.isEmpty()) {
				String oneLcs = lcses.iterator().next();
				if (oneLcs.length() < minLen) {
					minLen = oneLcs.length();
					minStrs = lcses;
				} else if (oneLcs.length() == minLen) {
					minStrs.addAll(lcses);
				}
			}
		}
		System.out.println("Min len using LCA of perm str\t" + minLen);
		System.out.println("Min strings using LCA of perm str\t" + minStrs);*/

		int minLen = Integer.MAX_VALUE;
		String minStr = null;
		System.out.println("Common sequences in input strings:\t" + commonSeqs);
		System.out.println(System.currentTimeMillis() - time);
		for (int i = 0; i < maxVal; i++) {
			StrBuilder builder = new StrBuilder(
					StringUtils.reverse(Integer.toBinaryString(i).replaceAll("0", "a").replaceAll("1", "b")));
			while (builder.length() != n) {
				builder.append('a');
			}
			String perm = builder.toString();
			int maxLen = Integer.MIN_VALUE;
			for (String comSeq : commonSeqs) {
				String lcs = ListUtils.longestCommonSubsequence(perm, comSeq);
				if (lcs.length() > maxLen) {
					maxLen = lcs.length();
				}
			}
			if (maxLen < minLen) {
				minStr = perm;
				minLen = maxLen;
			}
		}
		System.out.println("Min len using contains subseq\t" + minStr);
		System.out.println("Min string using contains subseq\t" + minLen);
		System.out.println(System.currentTimeMillis() - time);
	}

	private static HashSet<String> findLcs(boolean printTree) {
		int indices[] = new int[k];
		for (int i = 0; i < indices.length; i++) {
			indices[i] = -1;
		}
		tree = new DelegateTree<>();
		edgeCounter = 0;
		vertexCounter = 0;
		tree.setRoot(new LCSVertex(vertexCounter++, indices, ' '));
		traverse(tree.getRoot());
		//		if (printTree)
		//			GraphUtil.printTree(tree, new TreeLayout<>(tree));

		if (!printTree) {
			Collection<LCSVertex> vertices = tree.getVertices();
			HashSet<String> lcses = new HashSet<>();
			int lcsLen = -1;
			HashSet<LCSVertex> leaves = new HashSet<>();
			for (LCSVertex vertex : vertices) {
				if (tree.isLeaf(vertex)) {
					LCSVertex node = vertex;
					int ct = 0;
					while (node.c != ' ') {
						node = tree.getParent(node);
						ct++;
					}
					if (ct > lcsLen)
						lcsLen = ct;
					leaves.add(vertex);
				}
			}
			for (LCSVertex leaf : leaves) {
				LCSVertex node = leaf;
				char lcs[] = new char[lcsLen];
				int ct = 0;
				while (node.c != ' ') {
					lcs[ct++] = node.c;
					node = tree.getParent(node);
				}
				if (ct == lcsLen) {
					lcses.add(StringUtils.reverse(new String(lcs)));
				}
			}
			//		System.out.println(lcses);
			return lcses;
		} else {
			Collection<LCSVertex> vertices = tree.getVertices();
			HashSet<String> commonSubsequences = new HashSet<>();
			for (LCSVertex vertex : vertices) {
				if (tree.isLeaf(vertex)) {
					LCSVertex node = vertex;
					char seq[] = new char[n];
					int ct = 0;
					while (node.c != ' ') {
						node = tree.getParent(node);
						seq[ct++] = node.c;
					}
					commonSubsequences.add(StringUtils.reverse(new String(seq).trim()));
				}
			}
			return commonSubsequences;
		}
	}

	static boolean containsSequence(CharSequence str, CharSequence seq) {
		int strInd = 0, seqInd = 0, strLen = str.length(), seqLen = seq.length();
		while (strInd < strLen && seqInd < seqLen) {
			if (str.charAt(strInd) == seq.charAt(seqInd))
				seqInd++;
			strInd++;
		}
		if (seqInd == seqLen)
			return true;
		else
			return false;
	}

	static void traverse(LCSVertex v) {
		for (int i = 0; i < 2; i++) {
			boolean isFeasible = true;
			int[] newIndices = new int[k];
			for (int j = 0; j < k; j++) {
				int lastInd = v.indices[j];
				while (++lastInd < n && strs[j][lastInd] != 'a' + i)
					;
				if (lastInd == n) {
					isFeasible = false;
					break;
				}
				newIndices[j] = lastInd;
			}
			if (isFeasible) {
				LCSVertex child = new LCSVertex(vertexCounter++, newIndices, (char) ('a' + i));
				tree.addChild(edgeCounter++, v, child);
				traverse(child);
			}
		}
	}
}

class LCSVertex {
	int[] indices;
	int id;
	char c;

	public LCSVertex(int id, int[] indices, char c) {
		this.id = id;
		this.indices = indices;
		this.c = c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		LCSVertex other = (LCSVertex) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		int sum = 0;
		for (int i = 0; i < indices.length; i++) {
			sum += indices[i];
		}
		return c + "\n" + sum;
	}
}