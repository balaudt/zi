package zi.leet.medium;

import zi.leet.ds.TreeNode;

import java.util.TreeMap;

//https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
public class MaxDiffAncestor {

    private int result = Integer.MIN_VALUE;

    public int maxAncestorDiff(TreeNode root) {
        traverse(root, new TreeMap<>());
        return result;
    }

    private void traverse(TreeNode node, TreeMap<Integer, Integer> numbers) {
        if (node == null) return;
        boolean shouldRemove = true;
        int frequency = -1;
        if (numbers.containsKey(node.val)) {
            frequency = numbers.get(node.val);
            numbers.put(node.val, frequency + 1);
            shouldRemove = false;
        } else {
            numbers.put(node.val, 1);
        }
        result = Math.max(result, numbers.lowerKey(100001) - numbers.higherKey(-1));
        traverse(node.left, numbers);
        traverse(node.right, numbers);
        if (shouldRemove)
            numbers.remove(node.val);
        else
            numbers.put(node.val, frequency);
    }
}
