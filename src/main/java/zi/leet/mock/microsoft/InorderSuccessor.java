package zi.leet.mock.microsoft;

import zi.leet.ds.TreeNode;

import java.util.Stack;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/inorder-successor-in-bst/
public class InorderSuccessor {
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		Stack<TreeNode> parents = new Stack<>();
		if (p.right != null) return leftMostNode(p.right);
		traverse(root, p, parents);
		if (p == root) return null;
		TreeNode last = p;
		while (!parents.isEmpty()) {
			TreeNode parent = parents.pop();
			if (parent.left == last) return parent;
			last = parent;
		}
		return null;
	}

	private boolean traverse(TreeNode node, TreeNode e, Stack<TreeNode> s) {
		if (node == e) {
			return true;
		}
		s.add(node);
		boolean flag = false;
		if (node.left != null) flag = traverse(node.left, e, s);
		if (!flag && node.right != null) flag = traverse(node.right, e, s);
		if (!flag) s.remove(node);
		return flag;
	}

	private TreeNode leftMostNode(TreeNode p) {
		if (p == null) return null;
		if (p.left != null) return leftMostNode(p.left);
		return p;
	}

}
