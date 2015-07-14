package zi.hrank;

import java.io.BufferedReader;
import java.io.FileReader;

public class MaxSubArray {

	static int in[];

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/ground.txt"));
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			inStr = reader.readLine().split(" ");
			in = new int[n];
			int nonContSum = 0, maxNeg = Integer.MIN_VALUE;
			for (int j = 0; j < n; j++) {
				in[j] = Integer.parseInt(inStr[j]);
				if (in[j] > 0)
					nonContSum += in[j];
				else if (in[j] > maxNeg)
					maxNeg = in[j];
			}
			nonContSum = nonContSum > 0 ? nonContSum : maxNeg;
			int contSum = maxContArray(0, n - 1);
			System.out.println(contSum + " " + nonContSum);
		}
		reader.close();
	}

	static int maxContArray(int low, int high) {
		if (low == high)
			return in[low];
		int mid = (low + high) / 2;
		int lmax = maxContArray(low, mid);
		int rmax = maxContArray(mid + 1, high);
		int cmax = crossingMax(low, high, mid);
		if (lmax > rmax && lmax > cmax)
			return lmax;
		else if (rmax > lmax && rmax > cmax)
			return rmax;
		else
			return cmax;
	}

	static int crossingMax(int low, int high, int mid) {
		int sum = in[mid], maxSum = sum;
		for (int i = mid - 1; i >= low; i--) {
			sum += in[i];
			if (sum > maxSum)
				maxSum = sum;
		}
		int lSum = maxSum;
		sum = in[mid + 1];
		maxSum = sum;
		for (int i = mid + 2; i <= high; i++) {
			sum += in[i];
			if (sum > maxSum)
				maxSum = sum;
		}
		return lSum + maxSum;
	}
}
