package zi.hrank;

import java.io.BufferedReader;
import java.io.FileReader;

public class QuickSort {

	static int[] in;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/ground.txt"));
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		String[] inStr = reader.readLine().split(" ");
		in = new int[n];
		for (int i = 0; i < inStr.length; i++) {
			in[i] = Integer.parseInt(inStr[i]);
		}
		partition(0, n - 1);
		reader.close();
	}

	static void partition(int left, int right) {
		if (left >= right)
			return;
		int l = left, r = right;
		int pivot = in[left];
		left++;
		while (left <= right) {
			while (left <= r && in[left] <= pivot)
				left++;
			while (right >= l && in[right] > pivot)
				right--;
			if (left <= right) {
				int temp = in[left];
				in[left] = in[right];
				in[right] = temp;
				left++;
				right--;
			}
		}
		in[l] = in[right];
		in[right] = pivot;
		partition(l, right - 1);
		partition(left, r);
		print(l, r);
	}

	static void print(int left, int right) {
		for (int j = left; j <= right; j++) {
			System.out.print(in[j] + " ");
		}
		System.out.println();
	}
}
