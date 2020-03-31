package zi.leet.easy;

import zi.leet.ds.TreeNode;

import java.util.AbstractMap;
import java.util.Map;

//https://leetcode.com/problems/diameter-of-binary-tree
public class DiameterBinaryTree {

    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        return maxDepth(root);
    }

    private int maxDepth(TreeNode node) {
        if (node == null) return 0;
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        diameter = Math.max(diameter, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
