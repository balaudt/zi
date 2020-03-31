package zi.leet.easy;

import zi.leet.ds.TreeNode;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//https://leetcode.com/problems/cousins-in-binary-tree
public class CousinsInB {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<Map.Entry<TreeNode, TreeNode>> q = new LinkedList<>();
        q.offer(new AbstractMap.SimpleEntry<>(null, root));
        while (!q.isEmpty()) {
            boolean isX = false, isY = false;
            TreeNode xP = null, yP = null;
            Queue<Map.Entry<TreeNode, TreeNode>> next = new LinkedList<>();
            for (Map.Entry<TreeNode, TreeNode> t : q) {
                TreeNode p = t.getKey();
                TreeNode node = t.getValue();
                if (node.left != null) {
                    next.offer(new AbstractMap.SimpleEntry<>(node, node.left));
                }
                if (node.right != null) {
                    next.offer(new AbstractMap.SimpleEntry<>(node, node.right));
                }
                if (node.val == x) {
                    isX = true;
                    xP = p;
                } else if (node.val == y) {
                    isY = true;
                    yP = p;
                }
            }
            if (isX && isY)
                return xP != yP;
            else if ((isX && !isY) || (!isX && isY))
                return false;
            else
                q = next;
        }
        return false;
    }
}
