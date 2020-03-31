package zi.leet.mock.microsoft;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

/**
 * @author balamurugan
 */
public class MergeSortedArraysTest {

	@Test
	public void testMerge() {
		int[] nums1 = {1, 2, 3, 0, 0, 0};
		new MergeSortedArrays().merge(nums1, 3, new int[]{2, 5, 6}, 3);
		assertTrue(Arrays.equals(nums1, new int[]{1, 2, 2, 3, 5, 6}));
	}
}
