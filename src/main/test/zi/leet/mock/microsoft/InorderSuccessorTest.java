package zi.leet.mock.microsoft;

import org.testng.annotations.Test;
import zi.leet.ds.TreeNode;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author balamurugan
 */
public class InorderSuccessorTest {

	@Test
	public void testInorderSuccessor() {
		InorderSuccessor inorderSuccessor = new InorderSuccessor();
		TreeNode bst = TreeNode.createBST(new Integer[]{5, 3, 6, 1, 4, null, null, null, 2});
		assertEquals(inorderSuccessor.inorderSuccessor(bst, bst.left.right), bst);
	}
}
