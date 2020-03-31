package zi.leet.hard;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class ConstructTreeTest {
	private ConstructTree solution = new ConstructTree();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{new int[]{1, 2, 3, 4, 5}, new int[]{2, 1, 5, 4, 3}}
		};
	}

	@Test(dataProvider = "data")
	public void testBuildTree(int[] inorder, int[] postorder) {
		solution.buildTree(inorder, postorder);
	}
}
