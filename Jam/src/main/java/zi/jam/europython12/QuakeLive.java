package zi.jam.europython12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import org.apache.commons.lang3.text.StrBuilder;

public class QuakeLive {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/9/A-small-practice-0.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/9/A-small-practice-0.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			int n = Integer.parseInt(inStr[0]);
			int[] in = new int[n];
			for (int j = 0; j < n; j++) {
				in[j] = Integer.parseInt(inStr[j + 1]);
			}
			if (in.length == 2) {
				builder.append(Math.abs(in[0] - in[1])).append("\n");
				System.out.print(builder.toString());
				writer.write(builder.toString());
				continue;
			}
			Arrays.sort(in);
			long sum1 = in[0] + in[in.length - 1], sum2 = 0;
			for (int j = 1; j < in.length - 1; j += 2) {
				if (((j - 1) / 2) % 2 == 0)
					sum2 += in[j] + in[j + 1];
				else
					sum1 += in[j] + in[j + 1];
			}
			builder.append(Math.abs(sum2 - sum1)).append("\n");
			System.out.print(builder.toString());
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}
}
