package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import zi.leet.ds.TreeNode;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class LargestBSTSubTest {
	private LargestBSTSub solution = new LargestBSTSub();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
//				{TreeNode.createBST(new Integer[]{10, 5, 15, 1, 8, null, 7}), 3},
				{TreeNode.createBST(new Integer[]{3, 2, 4, null, null, 1, null}), 2}
		};
	}

	@Test(dataProvider = "data")
	public void testLargestBSTSubtree(TreeNode in, int expected) {
		assertEquals(solution.largestBSTSubtree(in), expected);
	}
}
