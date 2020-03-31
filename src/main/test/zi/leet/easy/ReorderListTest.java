package zi.leet.easy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import zi.leet.ds.ListNode;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class ReorderListTest {
	private ReorderList solution = new ReorderList();

	@DataProvider
	public static Object[][] data() {
		return new Object[][]{
				{ListNode.createList(new int[]{1, 2, 3, 4, 5})}
		};
	}

	@Test(dataProvider = "data")
	public void testReorderList(ListNode node) {
		solution.reorderList(node);
	}
}
