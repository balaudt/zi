package zi.leet.easy;

import zi.leet.ds.TreeNode;

import java.util.AbstractMap;
import java.util.Map;

//https://leetcode.com/problems/balanced-binary-tree
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return isBalancedWithHeight(root).getKey();
    }

    private Map.Entry<Boolean, Integer> isBalancedWithHeight(TreeNode root) {
        if (root == null) return new AbstractMap.SimpleEntry<>(true, 0);
        Map.Entry<Boolean, Integer> l = isBalancedWithHeight(root.left);
        Map.Entry<Boolean, Integer> r = isBalancedWithHeight(root.right);
        return new AbstractMap.SimpleEntry<>(l.getKey() && r.getKey() && Math.abs(l.getValue() - r.getValue()) <= 1,
                Math.max(l.getValue(), r.getValue()) + 1);
    }
}
