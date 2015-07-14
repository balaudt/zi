package zi.jam.y14.r1B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class LotterySmall {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("B-small-attempt0.bin"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("B-small-attempt0.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			// System.out.println(i);
			String[] inps = reader.readLine().split(" ");
			int a = Integer.parseInt(inps[0]);
			int b = Integer.parseInt(inps[1]);
			int k = Integer.parseInt(inps[2]);
			int comp[] = new int[k];
			for (int j = 0; j < k; j++) {
				comp[j] = j;
			}
			Arrays.sort(comp);
			long count = 0;
			for (int j = 0; j < a; j++) {
				for (int l = 0; l < b; l++) {
					if (Arrays.binarySearch(comp, j & l) > -1) {
						count++;
					}
				}
			}
			StringBuilder builder = new StringBuilder("Case #").append(i + 1).append(": ").append(count).append('\n');
			writer.write(builder.toString());
//			System.out.println(count);
		}
		reader.close();
		writer.close();
	}

}
