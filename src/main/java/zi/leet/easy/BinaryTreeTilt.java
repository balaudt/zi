package zi.leet.easy;

import zi.leet.ds.TreeNode;

//https://leetcode.com/problems/binary-tree-tilt
public class BinaryTreeTilt {

    private int tiltSum = 0;

    public int findTilt(TreeNode root) {
        tiltSum = 0;
        sum(root);
        return tiltSum;
    }

    private int sum(TreeNode node) {
        if (node == null) return 0;
        int left = sum(node.left);
        int right = sum(node.right);
        tiltSum += Math.abs(left - right);
        return node.val + left + right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(new BinaryTreeTilt().findTilt(root));
    }
}
