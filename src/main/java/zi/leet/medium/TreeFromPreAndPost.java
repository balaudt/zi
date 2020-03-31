package zi.leet.medium;

import zi.leet.ds.TreeNode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
public class TreeFromPreAndPost {

    int[] pre, post;
    int n;
    Map<Integer, Integer> preMap = new HashMap<>();
    Map<Integer, Integer> postMap = new HashMap<>();

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        n = pre.length;
        for (int i = 0; i < pre.length; i++) {
            preMap.put(pre[i], i);
            postMap.put(post[i], i);
        }
        return constructSubTree(0, n - 1, 0, n - 1);
    }

    private TreeNode constructSubTree(int preStart, int preEnd, int postStart, int postEnd) {
        return null;
    }
}
