package zi.leet.w92;

import zi.leet.ds.TreeNode;

/*
 *@author balamurugan
 */
public class SmallestSubtree {

	class TreeNodeWithDepth {
		int depth;
		int val;
		TreeNodeWithDepth left;
		TreeNodeWithDepth right;

		TreeNodeWithDepth(TreeNode node) {
			this.val = node.val;
			this.depth = 1;
		}
	}

	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		TreeNodeWithDepth treeWithDepth = generateNewTree(root);
		return subtreeWithAllDeepest(root, treeWithDepth);
	}

	private TreeNode subtreeWithAllDeepest(TreeNode node, TreeNodeWithDepth nodeWithDepth) {
		if (nodeWithDepth.left == null && nodeWithDepth.right == null) {
			return node;
		}
		if (nodeWithDepth.right == null) {
			return subtreeWithAllDeepest(node.left, nodeWithDepth.left);
		}
		if (nodeWithDepth.left == null) {
			return subtreeWithAllDeepest(node.right, nodeWithDepth.right);
		}
		int leftDepth = nodeWithDepth.left.depth;
		int rightDepth = nodeWithDepth.right.depth;
		if (leftDepth == rightDepth) {
			return node;
		}
		if (leftDepth > rightDepth) {
			return subtreeWithAllDeepest(node.left, nodeWithDepth.left);
		} else {
			return subtreeWithAllDeepest(node.right, nodeWithDepth.right);
		}
	}

	private TreeNodeWithDepth generateNewTree(TreeNode node) {
		if (node == null) return null;
		TreeNodeWithDepth newNode = new TreeNodeWithDepth(node);
		if (node.left == null && node.right == null) {
			return newNode;
		}
		int depth = -1;
		if (node.left != null) {
			newNode.left = generateNewTree(node.left);
			depth = newNode.left.depth + 1;
		}
		if (node.right != null) {
			newNode.right = generateNewTree(node.right);
			depth = Math.max(depth, newNode.right.depth + 1);
		}
		newNode.depth = depth;
		return newNode;
	}
}
