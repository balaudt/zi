package zi.jam.apac15.rA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.apache.commons.lang3.text.StrBuilder;

public class SevenSegmentDisplay {

	static int[] numDisps = new int[] { 0x7B, 0X7F, 0X70, 0X5F, 0X5B, 0X33, 0X79, 0X6D, 0X30, 0X7E };

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				SevenSegmentDisplay.class.getResourceAsStream("/A-small-practice.in")));
		BufferedWriter writer = new BufferedWriter(new FileWriter("A-small-practice.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			System.out.println("Case " + (i + 1));
			writer.write(new StrBuilder("Case #").append(i + 1).append(": ").toString());
			String[] line = reader.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			int[] in = new int[n];
			for (int j = 0; j < n; j++) {
				in[j] = Integer.parseInt(line[j + 1], 2);
			}
			// System.out.println(Arrays.toString(in));
			int mainIndex = 0, nextD = -1, mainDiff = 0;
			boolean unAmbRes = true;
			int diff = 0;
			while (mainIndex < numDisps.length) {
				int start = -1, j;
				for (j = mainIndex; j < numDisps.length; j++) {
					if ((~numDisps[j] & in[0]) == 0) {
						diff = numDisps[j] & ~in[0];
						start = j;
						break;
					}
				}
				// System.out.println(start);
				mainIndex = j + 1;
				if (start == -1) {
					continue;
				} else {
					int mainStart = start;
					boolean isPossible = true;
					for (int k = 1; k < in.length; k++) {
						start++;
						if (start == 10)
							start = 0;
						if ((~numDisps[start] & in[k]) != 0 || (in[k] & ~diff) != in[k]) {
							isPossible = false;
							break;
						} else {
							int currDiff = ~in[k] & numDisps[start];
							if ((currDiff | diff) > diff) {
								diff = currDiff;
								k = 0;
								start = mainStart;
								continue;
							}
						}
					}
					if (!isPossible) {
						continue;
					} else {
						if (nextD != -1) {
							unAmbRes = false;
							break;
						} else {
							nextD = (start == 10 ? 0 : start);
							mainDiff = diff;
						}
					}
				}
			}
			if (unAmbRes && nextD != -1) {
				// System.out.println("ans=" + Integer.toString(numDisps[nextD +
				// 1] & ~mainDiff, 2));
				String ans = String.format("%7s", Integer.toBinaryString(numDisps[nextD + 1] & ~mainDiff)).replace(' ', '0');
				System.out.println(ans);
				writer.write(ans + "\n");
				// System.out.println("diff=" + mainDiff);
			} else {
				System.out.println("ERROR");
				writer.write("ERROR!\n");
			}
		}
		reader.close();
		writer.flush();
		writer.close();
	}
}
