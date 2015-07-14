package zi.jam.y15.r1A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.commons.lang3.text.StrBuilder;

public class A {

	public static final String FOLDER_ROOT = "C:/ft/1/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "A-large.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "A-large.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			int n = Integer.parseInt(reader.readLine());
			int in[] = new int[n];
			String[] inStr = reader.readLine().split(" ");
			int maxNegDiff = 0, minAte1 = 0;
			in[0] = Integer.parseInt(inStr[0]);
			for (int j = 1; j < in.length; j++) {
				in[j] = Integer.parseInt(inStr[j]);
				int diff = in[j] - in[j - 1];
				if (diff < 0) {
					diff *= -1;
					if (diff > maxNegDiff) {
						maxNegDiff = diff;
					}
					minAte1 += diff;
				}
			}
			System.out.println(maxNegDiff);
			int minAte2 = 0;
			for (int j = 1; j < inStr.length; j++) {
				// int diff = in[j] - in[j - 1];
				if (in[j - 1] > maxNegDiff)
					minAte2 += maxNegDiff;
				else
					minAte2 += in[j - 1];
				// if (diff >= 0) {
				// if (in[j - 1] < maxNegDiff)
				// minAte2 += in[j - 1];
				// else
				// minAte2 += maxNegDiff;
				// } else {
				// if (in[j - 1] > maxNegDiff)
				// minAte2 += diff * -1;
				// }
			}
			builder.append(minAte1).append(' ').append(minAte2).append('\n');
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}
}
