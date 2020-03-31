package zi.leet.easy;

import zi.leet.ds.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/average-of-levels-in-binary-tree/
public class AverageLevelsBT {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> out = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            Queue<TreeNode> nextNodes = new LinkedList<>();
            double sum = 0;
            for (TreeNode node : nodes) {
                sum += node.val;
                if (node.left != null) {
                    nextNodes.offer(node.left);
                }
                if (node.right != null) {
                    nextNodes.offer(node.right);
                }
            }
            out.add(sum / nodes.size());
            nodes = nextNodes;
        }
        return out;
    }
}
