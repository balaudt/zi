package zi.leet.hard;

import org.testng.annotations.Test;
import zi.leet.ds.TreeNode;

import static org.testng.Assert.*;

/**
 * @author balamurugan
 */
public class BSTDeserializerTest {
	private BSTDeserializer solution = new BSTDeserializer();
	private TreeNode bst = TreeNode.createBST(new Integer[]{2, 1, 4, null, 9, 5, 4, null, 12, 13, null});
	private String serializedString = "2,3|1,2|4,3|9,2|5,1|4,0|12,0|13,0";

	@Test
	public void testSerialize() {
		assertEquals(solution.serialize(bst), serializedString);
	}

	@Test
	public void testDeserialize() {
		assertEquals(solution.deserialize(serializedString), bst);
	}
}
