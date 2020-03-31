package zi.leet.easy;

import zi.leet.ds.TreeNode;

//https://leetcode.com/problems/convert-bst-to-greater-tree
public class BSTToGreaterTree {

    public TreeNode convertBST(TreeNode root) {
        sum(root, 0);
        return root;
    }

    public int sum(TreeNode node, int add) {
        if (node == null) {
            return 0;
        }
        int rightSum = sum(node.right, 0);
        int leftSum = sum(node.left, add + rightSum);
        int out = node.val + leftSum + rightSum;
        node.val += rightSum + add;
        return out;
    }
}
