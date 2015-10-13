package zi.hrank.algo.gt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Array;

public class FloydWarshall {
	static final int P = (int) 1e8;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("/home/bala/temp/fw.in"));
		System.setOut(new PrintStream(new FileOutputStream("/home/bala/temp/fw.out")));

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int m = Integer.parseInt(inStr[1]);
		int[][] w = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				w[i][j] = P + 1;
		for (int i = 0; i < m; i++) {
			inStr = reader.readLine().split(" ");
			int x = Integer.parseInt(inStr[0]) - 1;
			int y = Integer.parseInt(inStr[1]) - 1;
			int r = Integer.parseInt(inStr[2]);
			if (w[x][y] > r)
				w[x][y] = r;
		}
		int[][] dPrev = w;
		for (int i = 0; i < n; i++) {
			int[][] d = new int[n][n];
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					d[j][k] = Math.min(dPrev[j][k], dPrev[j][i] + dPrev[i][k]);
				}
			}
			dPrev = d;
		}
		int q = Integer.parseInt(reader.readLine());
		for (int i = 0; i < q; i++) {
			inStr = reader.readLine().split(" ");
			int s = Integer.parseInt(inStr[0]) - 1;
			int dest = Integer.parseInt(inStr[1]) - 1;
			int weigh = dPrev[s][dest];
			if (s == dest)
				System.out.println("0");
			else if (weigh > P)
				System.out.println("-1");
			else
				System.out.println(weigh);
		}
		reader.close();
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] deepCopyOf(T[] array) {
		if (0 >= array.length)
			return array;
		return (T[]) deepCopyOf(array, Array.newInstance(array[0].getClass(), array.length), 0);
	}

	private static Object deepCopyOf(Object array, Object copiedArray, int index) {
		if (index >= Array.getLength(array))
			return copiedArray;
		Object element = Array.get(array, index);
		if (element.getClass().isArray()) {
			Array.set(copiedArray, index,
					deepCopyOf(element, Array.newInstance(element.getClass().getComponentType(), Array.getLength(element)), 0));
		} else {
			Array.set(copiedArray, index, element);
		}
		return deepCopyOf(array, copiedArray, ++index);
	}
}
