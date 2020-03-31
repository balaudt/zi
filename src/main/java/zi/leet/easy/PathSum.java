package zi.leet.easy;

import zi.leet.ds.TreeNode;

import java.util.*;

//https://leetcode.com/problems/path-sum
public class PathSum {
    private static TreeNode createBST(int[] input) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(input[0]);
        queue.offer(root);
        List<Integer> ignoreList = new ArrayList<>();
        for (int i = 1; i < input.length; i += 2) {
            TreeNode newNode1 = null, newNode2 = null;

            if (input[i] == Integer.MIN_VALUE) {
                if (ignoreList.contains(i)) {
                    continue;
                }
                ignoreList.add(2 * i + 1);
                ignoreList.add(2 * i + 2);
            } else {
                newNode1 = new TreeNode(input[i]);
            }

            if (input[i + 1] == Integer.MIN_VALUE) {
                if (ignoreList.contains(i + 1)) {
                    continue;
                }
                ignoreList.add(2 * (i + 1) + 1);
                ignoreList.add(2 * (i + 1) + 2);
            } else {
                newNode2 = new TreeNode(input[i + 1]);
            }

            TreeNode node = queue.poll();
            if (node == null) {
                return root;
            }
            node.left = newNode1;
            node.right = newNode2;
            if (null != newNode1) {
                queue.offer(newNode1);
            }
            if (null != newNode2) {
                queue.offer(newNode2);
            }
        }
        return root;
    }

    private int sumCount;
    private Map<Integer, Integer> runningSumWithFrequency;
    private int target;

    public int pathSum(TreeNode root, int sum) {
        sumCount = 0;
        runningSumWithFrequency = new HashMap<>();
        target = sum;

        pathSum(0, root);
        return sumCount;
    }

    private void pathSum(int currentRunningSum, TreeNode root) {
        if (root == null)
            return;
        int nextSum = currentRunningSum + root.val;
        if (nextSum == target)
            sumCount += 1;
        else if (runningSumWithFrequency.containsKey(nextSum - target))
            sumCount += runningSumWithFrequency.get(nextSum - target);
        boolean sumExists = runningSumWithFrequency.containsKey(nextSum);
        if (sumExists)
            runningSumWithFrequency.put(nextSum, runningSumWithFrequency.get(nextSum) + 1);
        else
            runningSumWithFrequency.put(nextSum, 1);
        pathSum(nextSum, root.left);
        pathSum(nextSum, root.right);
        if (sumExists)
            runningSumWithFrequency.put(nextSum, runningSumWithFrequency.get(nextSum) - 1);
        else
            runningSumWithFrequency.remove(nextSum);
    }

    public static void main(String[] args) {
        PathSum pathSum = new PathSum();
        System.out.println(pathSum.pathSum(createBST(new int[]{10, 5, -3, 3, 2, Integer.MIN_VALUE, 11, 3, -2, Integer.MIN_VALUE, 1}), 8));
    }
}
