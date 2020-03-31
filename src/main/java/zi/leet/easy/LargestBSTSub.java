package zi.leet.easy;

import zi.leet.ds.TreeNode;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class LargestBSTSub {
	private int out = 1;

	public int largestBSTSubtree(TreeNode root) {
		if (root == null)
			return 0;
		bstNode(root);
		return out;
	}

	private int[] bstNode(TreeNode node) {
		int[] result = new int[3];
		result[0] = 1;
		if (node.left != null) {
			int[] l = bstNode(node.left);
			if (l[0] != -1 && l[2] <= node.val) {
				result[0] += l[0];
				result[1] = l[1];
			} else
				result[0] = -1;
		} else
			result[1] = node.val;
		if (node.right != null) {
			int[] r = bstNode(node.right);
			if (result[0] != -1 && r[0] != -1 && r[1] >= node.val) {
				result[0] += r[0];
				result[2] = r[2];
			} else
				result[0] = -1;
		} else
			result[2] = node.val;
		out = Math.max(result[0], out);
		System.out.println(node.val + "\t" + Arrays.toString(result));
		return result;
	}
}
