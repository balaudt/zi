package zi.leet.hard;

import zi.leet.ds.TreeNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author balamurugan
 */
public class ConstructTree {
	private int[] inorder, postorder;

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if (inorder.length == 0)
			return null;
		this.inorder = inorder;
		this.postorder = postorder;
		return construct(0, inorder.length - 1, 0, inorder.length - 1);
	}

	private TreeNode construct(int inSt, int inEnd, int postSt, int postEnd) {
		System.out.println(Arrays.asList(inSt, inEnd, postSt, postEnd));
		if (inSt == inEnd)
			return new TreeNode(inorder[inSt]);
		TreeNode root = new TreeNode(postorder[postEnd]);
		int i = inSt;
		for (; i <= inEnd; i++)
			if (inorder[i] == root.val)
				break;
		if (i != inSt) {
			if (i == inEnd)
				root.left = construct(inSt, inEnd - 1, postSt, postEnd - 1);
			else {
				Set<Integer> leftNodes = new HashSet<>();
				for (int k = inSt; k < i; k++)
					leftNodes.add(inorder[k]);
				int j = postSt;
				for (; j <= postEnd; j++)
					if (!leftNodes.contains(postorder[j]))
						break;
				root.left = construct(inSt, i - 1, postSt, j - 1);
				root.right = construct(i + 1, inEnd, j, postEnd - 1);
			}
		} else {
			root.right = construct(inSt + 1, inEnd, postSt, postEnd - 1);
		}
		return root;
	}
}
