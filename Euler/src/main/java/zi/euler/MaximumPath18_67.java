package zi.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class MaximumPath18_67 {

	static final int N = 15;
	static Map<Integer, Integer> maxMap = new HashMap<Integer, Integer>();
	static int[] in = new int[N * (N + 1) / 2];

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("io/p018_triangle.txt"));
		String[] inStr = reader.readLine().split(" ");
		for (int i = 0; i < inStr.length; i++) {
			in[i] = Integer.parseInt(inStr[i]);
		}
		System.out.println(max(1));
		System.out.println(maxMap);
		reader.close();
	}

	static int max(Integer index) {
		if (maxMap.containsKey(index))
			return maxMap.get(index);
		double actualDepth = Math.sqrt(2 * index);
		int depth;
		if (actualDepth - Math.floor(actualDepth) < 0.414 && actualDepth - Math.floor(actualDepth) != 0)
			depth = (int) (Math.floor(actualDepth) + 1);
		else
			depth = (int) Math.floor(actualDepth);
		int lastTriangle = (depth - 1) * depth / 2;
		int nextTriangle = (depth + 1) * depth / 2;
		if (nextTriangle < index) {
			lastTriangle = nextTriangle;
			nextTriangle = (depth + 1) * (depth + 2) / 2;
			depth++;
		} else if (lastTriangle > index) {
			nextTriangle = lastTriangle;
			lastTriangle = (depth - 1) * (depth - 2) / 2;
			depth--;
		}
		int child1 = nextTriangle + index - lastTriangle;
		int child2 = child1 + 1;
		if (child1 > in.length) {
			maxMap.put(index, in[index - 1]);
			return in[index - 1];
		}
		int c1Res = max(child1);
		int c2Res = max(child2);
		int max = c1Res >= c2Res ? c1Res : c2Res;
		max += in[index - 1];
		maxMap.put(index, max);
		return max;
	}
}
