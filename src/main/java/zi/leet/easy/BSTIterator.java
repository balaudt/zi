package zi.leet.easy;

import zi.leet.ds.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author balamurugan
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class BSTIterator {
	private boolean hasNext;
	private TreeNode current;
	private Deque<TreeNode> parentStack;

	public BSTIterator(TreeNode root) {
		if (root == null) {
			hasNext = false;
			return;
		}
		hasNext = true;
		parentStack = new LinkedList<>();
		current = leftMostNode(root);
	}

	/**
	 * @return the next smallest number
	 */
	public int next() {
		int out = current.val;
		if (current.right != null) {
			parentStack.push(current);
			current = leftMostNode(current.right);
			return out;
		}
		TreeNode parent = current;
		while (!parentStack.isEmpty()) {
			current = parent;
			parent = parentStack.pop();
			if (parent.left == current) {
				current = parent;
				return out;
			}
		}
		hasNext = false;
		return out;
	}

	/**
	 * @return whether we have a next smallest number
	 */
	public boolean hasNext() {
		return hasNext;
	}

	private TreeNode leftMostNode(TreeNode n) {
		if (n.left == null) return n;
		parentStack.push(n);
		return leftMostNode(n.left);
	}
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

