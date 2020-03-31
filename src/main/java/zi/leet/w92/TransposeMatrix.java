package zi.leet.w92;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class TransposeMatrix {
	public int[][] transpose(int[][] A) {
		if (A.length == 0) {
			return A;
		}
		int[][] b = new int[A[0].length][A.length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				b[j][i] = A[i][j];
			}
		}
		return b;
	}

	public static void main(String[] args) {
		for (int[] ints : new TransposeMatrix().transpose(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})) {
			System.out.println(Arrays.toString(ints));
		}
	}
}
