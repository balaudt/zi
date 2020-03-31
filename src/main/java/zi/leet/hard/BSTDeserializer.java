package zi.leet.hard;

import zi.leet.ds.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
public class BSTDeserializer {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) return null;
		Queue<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		StringBuilder builder = new StringBuilder();
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			builder.append(node.val).append(",");
			int flag = 0;
			if (node.left != null) {
				q.add(node.left);
				flag |= 1;
			}
			if (node.right != null) {
				q.add(node.right);
				flag |= 2;
			}
			builder.append(flag).append('|');
		}
		return builder.deleteCharAt(builder.length() - 1).toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null) return null;
		String[] nodes = data.split("\\|");
		int i = 0;
		Queue<TreeNode> q = new ArrayDeque<>();
		TreeNode root = new TreeNode();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			String[] nodesStr = nodes[i++].split(",");
			node.val = Integer.parseInt(nodesStr[0]);
			int flag = Integer.parseInt(nodesStr[1]);
			if ((flag & 1) > 0) {
				node.left = new TreeNode();
				q.offer(node.left);
			}
			if ((flag & 2) > 0) {
				node.right = new TreeNode();
				q.offer(node.right);
			}
		}
		return root;
	}
}
