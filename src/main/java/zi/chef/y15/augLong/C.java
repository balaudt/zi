package zi.chef.y15.augLong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.BitSet;

public class C {

	public static void main(String[] args) throws Exception {
						BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/21/C.in"));
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		int[][] inCols;
		int n, h;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			n = Integer.parseInt(inStr[0]);
			h = Integer.parseInt(inStr[1]);
			inCols = new int[2][n];
			int j;
			for (j = 0; j < n; j++) {
				inStr = reader.readLine().split(" ");
				inCols[0][j] = Integer.parseInt(inStr[0]);
				inCols[1][j] = Integer.parseInt(inStr[1]) + 1;
			}
			Arrays.sort(inCols[0]);
			Arrays.sort(inCols[1]);
			//			System.out.println(Arrays.toString(inCols));
			ArrayDeque<Integer> rowStack = new ArrayDeque<>(h);
			long sum = 0;
			int lastEnergySpent = n;
			for (j = 0; j < h; j++) {
				lastEnergySpent = getEnergySpent(inCols, lastEnergySpent, j);
				rowStack.offer(lastEnergySpent);
				sum += lastEnergySpent;
			}
			long minSum = sum;
			for (; j < n; j++) {
				lastEnergySpent = getEnergySpent(inCols, lastEnergySpent, j);
				sum -= rowStack.poll();
				sum += lastEnergySpent;
				rowStack.offer(lastEnergySpent);
				if (sum < minSum)
					minSum = sum;
			}
			System.out.println(minSum);
		}
		reader.close();
	}

	static int getEnergySpent(int[][] inCols, int lastEnergySpent, int row) {
		lastEnergySpent -= getCount(inCols[0], row);
		lastEnergySpent += getCount(inCols[1], row);
		return lastEnergySpent;
	}

	static int getCount(int[] sorArr, int lkup) {
		int ip = Arrays.binarySearch(sorArr, lkup);
		if (ip < 0)
			return 0;
		int origIp = ip;
		int count = 1;
		ip--;
		while (ip >= 0 && sorArr[ip] == lkup) {
			count++;
			ip--;
		}
		ip = origIp + 1;
		while (ip < sorArr.length && sorArr[ip] == lkup) {
			count++;
			ip++;
		}
		return count;
	}

	static int getEnergySpent(int[][] inCols, int row) {
		int c = 0;
		for (int i = 0; i < inCols.length; i++) {
			if (row < inCols[i][0] || row > inCols[i][1])
				c++;
		}
		return c;
	}
}
