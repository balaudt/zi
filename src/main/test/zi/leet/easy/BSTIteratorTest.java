package zi.leet.easy;

import org.testng.annotations.Test;
import zi.leet.ds.TreeNode;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class BSTIteratorTest {

	@Test
	public void basicTest() {
		TreeNode root = TreeNode.createBST(new Integer[]{7, 3, 15, null, null, 9, 20});
		BSTIterator it = new BSTIterator(root);
		while (it.hasNext()) System.out.println(it.next());
	}

	@Test
	public void failTest1() {
		TreeNode root = TreeNode.createBST(new Integer[]{3, 1, 4, null, 2});
		BSTIterator it = new BSTIterator(root);
		while (it.hasNext()) System.out.println(it.next());
	}
}
