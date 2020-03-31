package zi.leet.medium;

import zi.leet.ds.TreeNode;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Stack;

public class BSTIterator {
    private Stack<Map.Entry<TreeNode, Boolean>> q = new Stack<>();

    public BSTIterator(TreeNode root) {
        if (root == null) return;
        q.push(new AbstractMap.SimpleEntry<>(root, false));
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        Map.Entry<TreeNode, Boolean> entry = q.pop();
        TreeNode node = entry.getKey();
        if (node.left != null && !entry.getValue()) {
            TreeNode n = node;
            q.push(new AbstractMap.SimpleEntry<>(node, true));
            while (n.left != null) {
                q.push(new AbstractMap.SimpleEntry<>(n, false));
                n = n.left;
            }
            int out = n.val;
            if (n.right != null) q.push(new AbstractMap.SimpleEntry<>(n.right, false));
            return out;
        } else {
            int out = node.val;
            if (node.right != null) q.push(new AbstractMap.SimpleEntry<>(node.right, false));
            return out;
        }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !q.isEmpty();
    }
}
