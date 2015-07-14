package zi.hrank;

import cern.colt.Arrays;

public class Sorts {
	public static void bubbleSort() {
		int[] in = new int[] { 54, 26, 93, 17, 77, 31, 44, 55, 20 };
		int n = in.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (in[j] > in[j + 1]) {
					swap(in, j, j + 1);
				}
			}
			System.out.println(Arrays.toString(in));
		}
	}

	public static void selectionSort() {
		int[] in = new int[] { 54, 26, 93, 17, 77, 31, 44, 55, 20 };
		int n = in.length;
		for (int i = 0; i < n - 1; i++) {
			int max = in[0], maxInd = 0;
			for (int j = 0; j < n - i; j++) {
				if (in[j] > max) {
					max = in[j];
					maxInd = j;
				}
			}
			swap(in, n - i - 1, maxInd);
			System.out.println(Arrays.toString(in));
		}
	}

	static void swap(int[] in, int x, int y) {
		int temp = in[x];
		in[x] = in[y];
		in[y] = temp;
	}
}
