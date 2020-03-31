package zi.leet.medium;

import zi.leet.ds.TreeNode;

import java.util.*;

//https://leetcode.com/problems/all-possible-full-binary-trees
public class FullBinaryTrees {

    private List<TreeNode> results;
    private int n;
    private TreeSet<TreeNode> processed = new TreeSet<>(new Comparator<TreeNode>() {
        @Override
        public int compare(TreeNode o1, TreeNode o2) {
            if (o1 == null && o2 == null) return 0;
            if (o1 != null && o2 == null) return 1;
            if (o1 == null) return -1;
            int left = compare(o1.left, o2.left);
            if (left != 0) return left;
            return compare(o1.right, o2.right);
        }
    });

    public List<TreeNode> allPossibleFBT(int N) {
        if (N % 2 == 0) return new ArrayList<>();
        n = N;
        results = new ArrayList<>();
        TreeNode root = new TreeNode(0);
        List<TreeNode> leaves = new ArrayList<>();
        leaves.add(root);
        generateResults(root, leaves, 1);
        return results;
    }

    private void generateResults(TreeNode root, List<TreeNode> leaves, int currentCount) {
        if (processed.contains(root))
            return;
        processed.add(root);
        if (currentCount == n) {
            results.add(root);
            return;
        }
        for (TreeNode leaf : leaves) {
            List<TreeNode> newLeaves = new ArrayList<>();
            Map.Entry<TreeNode, TreeNode> copy = copyOf(root, newLeaves, leaf);
            TreeNode leafCopy = copy.getValue();
            leafCopy.left = new TreeNode(0);
            leafCopy.right = new TreeNode(0);
            newLeaves.remove(leafCopy);
            newLeaves.add(leafCopy.left);
            newLeaves.add(leafCopy.right);
            generateResults(copy.getKey(), newLeaves, currentCount + 2);
        }
    }

    private Map.Entry<TreeNode, TreeNode> copyOf(TreeNode node, List<TreeNode> leaves, TreeNode srcLeaf) {
        if (node == null) return null;
        TreeNode out = new TreeNode(node.val);
        if (node.left == null) {
            leaves.add(out);
            return new AbstractMap.SimpleEntry<>(out, node.equals(srcLeaf) ? out : null);
        }
        Map.Entry<TreeNode, TreeNode> lCopy = copyOf(node.left, leaves, srcLeaf);
        out.left = lCopy.getKey();
        Map.Entry<TreeNode, TreeNode> rCopy = copyOf(node.right, leaves, srcLeaf);
        out.right = rCopy.getKey();
        return new AbstractMap.SimpleEntry<>(out, lCopy.getValue() == null ? rCopy.getValue() : lCopy.getValue());
    }

    public static void main(String[] args) {
        List<TreeNode> x = new FullBinaryTrees().allPossibleFBT(7);
        System.out.println(x);
    }
}
