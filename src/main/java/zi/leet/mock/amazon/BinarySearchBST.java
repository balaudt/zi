package zi.leet.mock.amazon;

import zi.leet.ds.TreeNode;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
public class BinarySearchBST {
	int k;
	TreeNode root;

	public boolean findTarget(TreeNode root, int k) {
		this.k = k;
		this.root = root;
		return traverse(root);
	}

	private boolean traverse(TreeNode node) {
		if (node == null) return false;
		if (binarySearch(root, k - node.val, node)) return true;
		if (traverse(node.left)) return true;
		return traverse(node.right);
	}

	private boolean binarySearch(TreeNode node, int t, TreeNode other) {
		if (node == null) return false;
		if (node.val == t && node != other) return true;
		if (node.val > t) return binarySearch(node.left, t, other);
		else return binarySearch(node.right, t, other);
	}
}
