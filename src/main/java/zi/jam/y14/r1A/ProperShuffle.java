package zi.jam.y14.r1A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;

public class ProperShuffle {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("C-sample.bin"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C-sample.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			double[] genRandoms = new double[n];
			int[] in = new int[n];
			int[] manipulation = new int[n];
			String[] inps = reader.readLine().split(" ");
			int repeatCount = 0, count = 0;
			for (int j = 0; j < inps.length; j++) {
				in[j] = Integer.parseInt(inps[j]);
				manipulation[j] = j;
			}
			for (int j = 0; j < inps.length - 1; j++) {
				int curNo = in[j];
				int ind = Ints.indexOf(manipulation, curNo);
				int temp = manipulation[j];
				manipulation[j] = manipulation[ind];
				manipulation[ind] = temp;
				int generatedNo = ind - j;
				double genRand = (double) n * (double) generatedNo / (double) (n - 1 - j);
				if (Doubles.contains(genRandoms, genRand)) {
					repeatCount++;
				} else {
					genRandoms[count++] = generatedNo;
				}
			}
			writer.write(new StringBuilder("Case #").append(i + 1).append(": ")
					.append(repeatCount).append(",")
					.append(repeatCount > 19 ? "BAD" : "GOOD").append('\n').toString());
			writer.write(Arrays.toString(genRandoms));
		}
		reader.close();
		writer.close();
	}
}
