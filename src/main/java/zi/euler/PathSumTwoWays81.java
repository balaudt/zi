package zi.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class PathSumTwoWays81 {
	static final int N = 80;
	static int[][] in = new int[N][N];
	static Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/1/p081_matrix.txt"));
		for (int i = 0; i < in.length; i++) {
			String[] str = reader.readLine().split(",");
			for (int j = 0; j < str.length; j++) {
				in[i][j] = Integer.parseInt(str[j]);
			}
		}
		System.out.println(getSum(0, 0));
		reader.close();
	}

	static int getSum(int r, int c) {
		Integer index = r * N + c;
		if (sumMap.containsKey(index))
			return sumMap.get(index);
		if (r == N - 1 && c == N - 1) {
			sumMap.put(index, in[r][c]);
			return in[r][c];
		}
		int sum = in[r][c];
		if (r == N - 1) {
			sum += getSum(r, c + 1);
			sumMap.put(index, sum);
			return sum;
		}
		if (c == N - 1) {
			sum += getSum(r + 1, c);
			sumMap.put(index, sum);
			return sum;
		}
		int colSum = getSum(r, c + 1);
		int rowSum = getSum(r + 1, c);
		sum += colSum < rowSum ? colSum : rowSum;
		sumMap.put(index, sum);
		return sum;
	}
}
