package zi.leet.medium;

import zi.leet.ds.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MergeBST {
    private static class StatefulNode {
        TreeNode n;
        boolean leftTraversed;

        StatefulNode(TreeNode n) {
            this.n = n;
        }

    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Deque<StatefulNode> n1 = new ArrayDeque<>();
        Deque<StatefulNode> n2 = new ArrayDeque<>();
        if (root1 != null)
            n1.push(new StatefulNode(root1));
        if (root2 != null)
            n2.push(new StatefulNode(root2));
        List<Integer> out = new ArrayList<>();
        TreeNode t;
        while (!n1.isEmpty() && !n2.isEmpty()) {
            if (!n1.peek().leftTraversed) {
                n1.peek().leftTraversed = true;
                if (n1.peek().n.left != null)
                    n1.push(new StatefulNode(n1.peek().n.left));
                continue;
            }

            if (!n2.peek().leftTraversed) {
                n2.peek().leftTraversed = true;
                if (n2.peek().n.left != null)
                    n2.push(new StatefulNode(n2.peek().n.left));
                continue;
            }

            if (n1.peek().n.val < n2.peek().n.val) {
                t = n1.pop().n;
                out.add(t.val);
                if (t.right != null)
                    n1.push(new StatefulNode(t.right));
            } else {
                t = n2.pop().n;
                out.add(t.val);
                if (t.right != null)
                    n2.push(new StatefulNode(t.right));
            }
        }

        while (!n1.isEmpty()) {
            if (!n1.peek().leftTraversed) {
                n1.peek().leftTraversed = true;
                if (n1.peek().n.left != null)
                    n1.push(new StatefulNode(n1.peek().n.left));
                continue;
            }
            t = n1.pop().n;
            out.add(t.val);
            if (t.right != null)
                n1.push(new StatefulNode(t.right));
        }

        while (!n2.isEmpty()) {
            if (!n2.peek().leftTraversed) {
                n2.peek().leftTraversed = true;
                if (n2.peek().n.left != null)
                    n2.push(new StatefulNode(n2.peek().n.left));
                continue;
            }
            t = n2.pop().n;
            out.add(t.val);
            if (t.right != null)
                n2.push(new StatefulNode(t.right));
        }
        return out;
    }

    public static void main(String[] args) {
        MergeBST bst = new MergeBST();
        System.out.println(bst.getAllElements(TreeNode.createBST(new Integer[]{2, 1, 4}), TreeNode.createBST(new Integer[]{1, 0, 3})));
        System.out.println(bst.getAllElements(TreeNode.createBST(new Integer[]{0, -10, 10}), TreeNode.createBST(new Integer[]{5, 1, 7, 0, 2})));
        System.out.println(bst.getAllElements(null, TreeNode.createBST(new Integer[]{5, 1, 7, 0, 2})));
        System.out.println(bst.getAllElements(TreeNode.createBST(new Integer[]{0, -10, 10}), null));
        System.out.println(bst.getAllElements(TreeNode.createBST(new Integer[]{1, null, 8}), TreeNode.createBST(new Integer[]{8, 1, null})));
    }
}
