package zi.chef.y15.decLong;
import java.awt.Color;
import java.awt.Paint;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;

import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateTree;
import zi.common.GraphUtil;

public class CTester {
	static int n, m;
	static char[][] in;
	static int vertexCounter = 0;
	static char[][] strs;
	static DelegateTree<LCSVertex, Integer> tree = new DelegateTree<>();
	static int edgeCounter = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/dec/C-sin.in"));
		InputStreamReader conReader = new InputStreamReader(System.in);
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/balaudt/Temp/dec/C-gen-cor.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			n = Integer.parseInt(reader.readLine());
			in = new char[n][];
			for (int j = 0; j < n; j++) {
				in[j] = reader.readLine().toCharArray();
			}
			m = in[0].length;

			HashSet<String> commonSeqs = getAllCommonSubseq();
//						System.out.println(commonSeqs);
			int maxVal = (int) Math.pow(2, m);
			int minLen = Integer.MAX_VALUE;
			String minStr = null;
			for (int j = 0; j < maxVal; j++) {
				StrBuilder builder = new StrBuilder(
						StringUtils.reverse(Integer.toBinaryString(j).replaceAll("0", "a").replaceAll("1", "b")));
				while (builder.length() != m) {
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
					minLen = maxLen;
					minStr = perm;
				}
			}
//						System.out.println(minStr);
			System.out.println(minLen);

			/*TreeSet<String> sorted = new TreeSet<>(new Comparator<String>() {
			
				@Override
				public int compare(String o1, String o2) {
					return o2.length() - o1.length();
				}
			});
			sorted.addAll(commonSeqs);
			System.out.println(sorted.isEmpty() ? 0 : sorted.iterator().next().length());*/

			//			System.out.println("----");

//			conReader.read();
//			visualizer.dispatchEvent(new WindowEvent(visualizer, WindowEvent.WINDOW_CLOSING));
		}
		reader.close();
		writer.close();
	}

	static HashSet<String> getAllCommonSubseq() {
		int indices[] = new int[n];
		for (int i = 0; i < indices.length; i++) {
			indices[i] = -1;
		}
		tree = new DelegateTree<>();
		edgeCounter = 0;
		vertexCounter = 0;
		tree.setRoot(new LCSVertex(vertexCounter++, indices, ' '));
		traverse(tree.getRoot());
		Collection<LCSVertex> vertices = tree.getVertices();
		HashSet<String> commonSubsequences = new HashSet<>();
		int treeDepth = Integer.MIN_VALUE;
		for (LCSVertex vertex : vertices) {
			if (tree.isLeaf(vertex)) {
				LCSVertex node = tree.getParent(vertex);
				int depth = tree.getDepth(node);
				if (depth > treeDepth)
					treeDepth = depth;
				char seq[] = new char[n];
				int ct = 0;
				while (node.c != ' ') {
					seq[ct++] = node.c;
					node = tree.getParent(node);
				}
				commonSubsequences.add(StringUtils.reverse(new String(seq).trim()));
			}
		}
		/*visualizer = GraphUtil.visualize(new TreeLayout<>(tree), new Transformer<LCSVertex, Paint>() {

			@Override
			public Paint transform(LCSVertex input) {
				if (input.c != '$')
					return Color.BLUE;
				else
					return Color.RED;
			}
		});*/
//		System.out.print(treeDepth + "\t");
		return commonSubsequences;
	}

	static JFrame visualizer;

	static void traverse(LCSVertex v) {
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
				LCSVertex child = new LCSVertex(vertexCounter++, newIndices, (char) ('a' + i));
				tree.addChild(edgeCounter++, v, child);
				traverse(child);
			} else {
				LCSVertex child = new LCSVertex(vertexCounter++, newIndices, '$');
				tree.addChild(edgeCounter++, v, child);
			}
		}
	}
}
