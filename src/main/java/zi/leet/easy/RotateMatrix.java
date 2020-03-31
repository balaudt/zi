package zi.leet.easy;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class RotateMatrix {
	public static void main(String[] args) {
		int n = 5;
		int[] cells = new int[n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				cells[c] = r * n + c;
			}
			System.out.println(Arrays.toString(cells));
		}
	}
}
