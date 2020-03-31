package zi.leet.easy;

import zi.leet.ds.TreeNode;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

import static zi.leet.ds.TreeNode.createBST;

//https://leetcode.com/problems/longest-univalue-path
public class LongestUnivaluePath {
    private int out = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return out;
        traverse(root);
        return out - 1;
    }

    private Map.Entry<Integer, Integer> traverse(TreeNode n) {
        if (n.left == null && n.right == null)
            return new AbstractMap.SimpleEntry<>(n.val, 1);
        Map.Entry<Integer, Integer> l = null;
        if (n.left != null)
            l = traverse(n.left);
        Map.Entry<Integer, Integer> r = null;
        if (n.right != null)
            r = traverse(n.right);
        int current = 0, frequency = 0;
        if (l != null && l.getKey() == n.val && r != null && r.getKey() == n.val) {
            current = l.getValue() + r.getValue() + 1;
            frequency = Math.max(l.getValue(), r.getValue()) + 1;
        } else if (l != null && l.getKey() == n.val)
            current = frequency = l.getValue() + 1;
        else if (r != null && r.getKey() == n.val)
            current = frequency = r.getValue() + 1;
        else
            current = frequency = 1;
        if (current > out)
            out = current;
        return new AbstractMap.SimpleEntry<>(n.val, frequency);
    }

    public static void main(String[] args) {
        LongestUnivaluePath path = new LongestUnivaluePath();
        System.out.println(path.longestUnivaluePath(createBST(new Integer[]{5, 4, 5, 1, 1, null, 5})));
    }

}
