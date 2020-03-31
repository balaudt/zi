package zi.leet.ds;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode() {
	}

	public TreeNode(int x) {
		val = x;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TreeNode treeNode = (TreeNode) o;
		return val == treeNode.val &&
				Objects.equals(left, treeNode.left) &&
				Objects.equals(right, treeNode.right);
	}

	@Override
	public int hashCode() {
		return Objects.hash(val, left, right);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("TreeNode{");
		sb.append("val=").append(val);
		sb.append('}');
		return sb.toString();
	}

	public static TreeNode createBST(Integer[] in) {
		TreeNode out = new TreeNode(in[0]);
		Queue<TreeNode> nodes = new ArrayDeque<>();
		nodes.add(out);
		int j = 1;
		while (!nodes.isEmpty() && j < in.length) {
			Queue<TreeNode> next = new ArrayDeque<>();
			for (TreeNode node : nodes) {
				if (in[j] != null) {
					node.left = new TreeNode(in[j]);
					next.add(node.left);
				}
				if (in[j + 1] != null) {
					node.right = new TreeNode(in[j + 1]);
					next.add(node.right);
				}
				j += 2;
				if (j >= in.length) break;
			}
			nodes = next;
		}
		return out;
	}
}
