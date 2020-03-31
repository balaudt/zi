package zi.leet.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author balamurugan
 */
public class SubmatrixSum {
	public int numSubmatrixSumTarget(int[][] matrix, int target) {
		int n = matrix[0].length, m = matrix.length;
		int result = 0;
		for (int l = 0; l < n; l++) {
			int[] t = new int[m];
			for (int r = l; r < n; r++) {
				for (int i = 0; i < m; i++) {
					t[i] += matrix[i][r];
				}
				result += lxrMatrices(t, target);
			}
		}
		return result;
	}

	private int lxrMatrices(int[] t, int target) {
		Map<Integer, Integer> sumFreq = new HashMap<>();
		int run = 0, out = 0;
		sumFreq.put(0, 1);
		for (int i = 0; i < t.length; i++) {
			run += t[i];
			if (sumFreq.containsKey(run - target))
				out += sumFreq.get(run - target);
			int ct = sumFreq.getOrDefault(run, 0);
			sumFreq.put(run, ct + 1);
		}
		return out;
	}
}
