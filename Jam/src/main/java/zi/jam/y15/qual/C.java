package zi.jam.y15.qual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Stack;

import org.apache.commons.lang3.text.StrBuilder;

import com.google.common.collect.ImmutableMap;

public class C {

	public static final String FOLDER_ROOT = "C:/ft/1/";

	static int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 2, -1, 4, -3 }, { 3, -4, -1, 2 }, { 4, 3, -2, -1 } };
	static ImmutableMap<Character, Integer> map = ImmutableMap.<Character, Integer> builder().put('1', 1).put('i', 2).put('j', 3)
			.put('k', 4).build();
	static char[] in;
	static Stack<Integer> stack;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "C-small-attempt0.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "C-small-attempt0.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			String[] inStr = reader.readLine().split(" ");
			int c = Integer.parseInt(inStr[0]);
			int n = Integer.parseInt(inStr[1]);
			String str = reader.readLine();
			in = new char[n * c];
			char[] inChars = str.toString().toCharArray();
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < inChars.length; k++) {
					in[j * inChars.length + k] = inChars[k];
				}
			}
			stack = new Stack<Integer>();
			int reqdResult = 2;
			int curInd = 0;
			boolean isPossible = false;
			boolean isPopped = false;
			do {
				if (stack.size() == 3) {
					if (stack.peek() == in.length || evaluate(stack.peek() + 1)) {
						isPossible = true;
						break;
					} else {
						reqdResult--;
						curInd = stack.pop() + 1;
						isPopped = true;
					}
				}
				int consume = consume(curInd, reqdResult, isPopped ? reqdResult : 1);
				if (consume == -1) {
					if (stack.isEmpty())
						break;
					else {
						reqdResult--;
						curInd = stack.pop() + 1;
						isPopped = true;
					}
				} else {
					stack.push(consume);
					reqdResult++;
					curInd = consume + 1;
					isPopped = false;
				}
			} while (true);
			if (isPossible)
				builder.append("YES");
			else
				builder.append("NO");
			builder.append('\n');
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}

	static boolean evaluate(int ind) {
		System.out.println("evaluate\t" + ind);
		int curRes = 1;
		boolean prevSign = false;
		while (ind < in.length) {
			int res = matrix[curRes - 1][map.get(in[ind]) - 1];
			if (res < 0) {
				if (prevSign)
					prevSign = false;
				else
					prevSign = true;
				curRes = res * -1;
			} else {
				curRes = res;
			}
			ind++;
		}
		if (curRes == 1 && !prevSign)
			return true;
		return false;
	}

	static int consume(int startIndex, int reqdRes, int curRes) {
		System.out.println("consume\t" + startIndex + "\t" + reqdRes + "\t" + curRes);
		System.out.println(stack);
		boolean prevSign = false;
		int ind = startIndex;
		while (ind < in.length) {
			int res = matrix[curRes - 1][map.get(in[ind]) - 1];
			if (res < 0) {
				if (prevSign)
					prevSign = false;
				else
					prevSign = true;
				curRes = res * -1;
			} else {
				curRes = res;
			}
			if (curRes == reqdRes && !prevSign) {
				return ind;
			}
			ind++;
		}
		return -1;
	}
}
