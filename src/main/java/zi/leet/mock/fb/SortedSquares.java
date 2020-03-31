package zi.leet.mock.fb;

import java.util.Arrays;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/squares-of-a-sorted-array/
public class SortedSquares {
	public int[] sortedSquares(int[] A) {
		int[] out = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			out[i]=A[i]*A[i];
		}
		Arrays.sort(out);
		return out;
	}
}
