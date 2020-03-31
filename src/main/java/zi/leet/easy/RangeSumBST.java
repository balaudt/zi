package zi.leet.easy;

import zi.leet.ds.TreeNode;

//https://leetcode.com/problems/range-sum-of-bst
public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        int sum = root.val >= L && root.val <= R ? root.val : 0;
        sum += rangeSumBST(root.left, L, R);
        sum += rangeSumBST(root.right, L, R);
        return sum;
    }
}
