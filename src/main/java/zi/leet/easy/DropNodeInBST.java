package zi.leet.easy;

import zi.leet.ds.TreeNode;

/**
 * @author balamurugan
 */
public class DropNodeInBST {

	public TreeNode deleteNode(TreeNode node, int key) {
		if (node == null) return null;
		if (node.val == key) {
			return drop(node);
		}
		if (node.val > key) {
			node.left = deleteNode(node.left, key);
		} else {
			node.right = deleteNode(node.right, key);
		}
		return node;
	}

	private TreeNode drop(TreeNode node) {
		if (node == null) return null;
		if (node.left == null && node.right == null) return null;
		if (node.left == null) return node.right;
		if (node.right == null) return node.left;
		TreeNode[] rightMostNodes = rightMostNode(node.left, node);
		TreeNode out = rightMostNodes[0];
		if (out == node.left) {
			node.left.right = node.right;
			return node.left;
		}
		if (out.left != null) {
			rightMostNodes[1].right = out.left;
		} else {
			rightMostNodes[1].right = null;
		}
		out.left = node.left;
		out.right = node.right;
		return out;
	}

	private TreeNode[] rightMostNode(TreeNode node, TreeNode parent) {
		if (node.right == null) return new TreeNode[]{node, parent};
		return rightMostNode(node.right, node);
	}
}
