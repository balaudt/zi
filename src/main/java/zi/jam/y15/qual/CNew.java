package zi.jam.y15.qual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Stack;

import org.apache.commons.lang3.text.StrBuilder;

import com.google.common.collect.ImmutableMap;

public class CNew {

	public static final String FOLDER_ROOT = "C:/ft/1/";

	static byte[][] matrix = new byte[][] { { 1, 2, 3, 4 }, { 2, -1, 4, -3 }, { 3, -4, -1, 2 }, { 4, 3, -2, -1 } };
	static ImmutableMap<Character, Byte> map = ImmutableMap.<Character, Byte> builder().put('1', (byte) 1).put('i', (byte) 2)
			.put('j', (byte) 3).put('k', (byte) 4).build();
	static char[] in;
	static Stack<Integer> stack;
	static byte[][] resultMatrix;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "C-small-attempt1.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "C-small-attempt1.out"));
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

			resultMatrix = new byte[n * c][n * c];
			for (int j = 0; j < resultMatrix.length; j++) {
				resultMatrix[j][j] = map.get(in[j]);
			}
			for (int j = 1; j < resultMatrix.length; j++) {
				for (int k = 0; j + k < resultMatrix.length; k++) {
					byte l1 = resultMatrix[k][j + k - 1];
					byte l2 = resultMatrix[j + k][j + k];
					boolean sign = false;
					if (l1 < 0) {
						sign = true;
						l1 *= -1;
					}
					if (l2 < 0) {
						sign = !sign;
						l2 *= -1;
					}
					resultMatrix[k][j + k] = matrix[l1 - 1][l2 - 1];
					if (sign)
						resultMatrix[k][j + k] *= -1;
				}
			}
//			for (int j = 0; j < resultMatrix.length; j++) {
//				System.out.println(j + "\t" + Arrays.toString(resultMatrix[j]));
//			}

			boolean isPossible = false;
			int j, k = 0;
			for (j = 0; j < resultMatrix.length - 2 && !isPossible; j++) {
				if (resultMatrix[0][j] != 2)
					continue;
				for (k = j + 1; k < resultMatrix.length - 1; k++) {
					if (resultMatrix[j + 1][k] == 3 && resultMatrix[k + 1][resultMatrix.length - 1] == 4) {
						// System.out.println(j + "\t" + k);
						isPossible = true;
						break;
					}
				}
			}
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

}
