package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import zi.leet.ds.ListNode;
import zi.leet.ds.TreeNode;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class DropNodeInBSTTest {
	private DropNodeInBST solution = new DropNodeInBST();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{TreeNode.createBST(new Integer[]{3, 1, 4, null, 2}), 3}
		};
	}

	@Test(dataProvider = "data")
	public void testDeleteNode(TreeNode in, int key) {
		System.out.println(solution.deleteNode(in, key));
	}
}
