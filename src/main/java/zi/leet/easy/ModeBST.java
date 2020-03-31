package zi.leet.easy;

import zi.leet.ds.TreeNode;

import java.util.*;

//https://leetcode.com/problems/find-mode-in-binary-search-tree
public class ModeBST {
    public int[] findMode(TreeNode root) {
        if (root == null) return null;
        Set<Integer> out = traverse(root).getKey();
        int[] result = new int[out.size()];
        Iterator<Integer> it = out.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = it.next();
        }
        return result;
    }

    private Map.Entry<Set<Integer>, Integer> traverse(TreeNode n) {
        if (n.left == null && n.right == null) {
            HashSet<Integer> out = new HashSet<>();
            out.add(n.val);
            return new AbstractMap.SimpleEntry<>(out, 1);
        }
        Map<Integer, Integer> frequencies = new HashMap<>();
        frequencies.put(n.val, 1);
        if (n.left != null) {
            Map.Entry<Set<Integer>, Integer> leftOut = traverse(n.left);
            leftOut.getKey().forEach(x -> frequencies.compute(x, (k, v) -> v == null ? leftOut.getValue() : v + leftOut.getValue()));
        }
        if (n.right != null) {
            Map.Entry<Set<Integer>, Integer> rightOut = traverse(n.right);
            rightOut.getKey().forEach(x -> frequencies.compute(x, (k, v) -> v == null ? rightOut.getValue() : v + rightOut.getValue()));
        }
        int max = Integer.MIN_VALUE;
        Set<Integer> result = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() == max) {
                result.add(entry.getKey());
            } else if (entry.getValue() > max) {
                max = entry.getValue();
                result = new HashSet<>();
                result.add(entry.getKey());
            }
        }
        return new AbstractMap.SimpleEntry<>(result, max);
    }

    public static void main(String[] args) {
        ModeBST modeBST = new ModeBST();
        System.out.println(Arrays.toString(modeBST.findMode(createBST(new Integer[]{1, null, 2, 2, null}))));
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
