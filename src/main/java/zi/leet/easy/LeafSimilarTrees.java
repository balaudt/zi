package zi.leet.easy;

import zi.leet.ds.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/leaf-similar-trees
public class LeafSimilarTrees {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        return leaves(root1).equals(leaves(root2));
    }

    private List<Integer> leaves(TreeNode r) {
        if (r == null) return Collections.emptyList();
        ArrayList<Integer> out = new ArrayList<>();
        if (r.left == null && r.right == null) {
            out.add(r.val);
        } else {
            out.addAll(leaves(r.left));
            out.addAll(leaves(r.right));
        }
        return out;
    }
}
