package zi.leet.mock.microsoft;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/merge-sorted-array/
public class MergeSortedArrays {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int l = m + n - 1, p1 = m - 1, p2 = n - 1;
		while (l >= 0) {
			if (p1 < 0) {
				nums1[l--] = nums2[p2--];
				continue;
			}
			if (p2 < 0) {
				nums1[l--] = nums1[p1--];
				continue;
			}
			if (nums1[p1] > nums2[p2]) {
				nums1[l--] = nums1[p1--];
			} else {
				nums1[l--] = nums2[p2--];
			}
		}
	}
}
