package zi.leet.medium;

import zi.leet.ds.TreeNode;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

//https://leetcode.com/problems/maximum-width-of-binary-tree
public class MaxWidthBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxWidth = Integer.MIN_VALUE;
        Queue<Map.Entry<TreeNode, Integer>> q = new ArrayDeque<>();
        q.add(new AbstractMap.SimpleEntry<>(root, 0));
        while (!q.isEmpty()) {
            Queue<Map.Entry<TreeNode, Integer>> next = new ArrayDeque<>();
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (Map.Entry<TreeNode, Integer> entry : q) {
                Integer pos = entry.getValue();
                min = Math.min(min, pos);
                max = Math.max(max, pos);
                TreeNode node = entry.getKey();
                if (node.left != null) next.add(new AbstractMap.SimpleEntry<>(node.left, 2 * pos));
                if (node.right != null) next.add(new AbstractMap.SimpleEntry<>(node.right, 2 * pos + 1));
            }
            q = next;
            maxWidth = Math.max(maxWidth, max - min);
        }
        return maxWidth + 1;
    }

    public static void main(String[] args) {
        MaxWidthBinaryTree tree = new MaxWidthBinaryTree();
        System.out.println(tree.widthOfBinaryTree(createBST(new Integer[]{1, 1, 1, 1, null, null, 1, 1, null, null, 1})));
    }

    private static TreeNode createBST(Integer[] in) {
        TreeNode out = new TreeNode(in[0]);
        Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.add(out);
        int j = 1;
        while (!nodes.isEmpty() && j < in.length) {
            Queue<TreeNode> next = new ArrayDeque<>();
            for (TreeNode node : nodes) {
                if (in[j] != null) {
                    node.left = new TreeNode(in[j]);
                    next.add(node.left);
                }
                if (in[j + 1] != null) {
                    node.right = new TreeNode(in[j + 1]);
                    next.add(node.right);
                }
                j += 2;
                if (j >= in.length) break;
            }
            nodes = next;
        }
        return out;
    }

}
