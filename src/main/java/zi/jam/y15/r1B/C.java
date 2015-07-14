package zi.jam.y15.r1B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.commons.lang3.text.StrBuilder;

public class C {

	public static final String FOLDER_ROOT = "C:/ft/20/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "C-small-1-attempt2.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "C-small-1-attempt2.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			int deg1, deg2;
			long sp1, sp2;
			String[] inStr;
			if (n == 0 || n == 1) {
				StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": 0\n");
				String builderStr = builder.toString();
				System.out.print(builderStr);
				writer.write(builderStr);
				if (n == 1) {
					reader.readLine();
				}
				continue;
			} else {
				inStr = reader.readLine().split(" ");
				deg1 = Integer.parseInt(inStr[0]);
				sp1 = Long.parseLong(inStr[2]);
				inStr = reader.readLine().split(" ");
				deg2 = Integer.parseInt(inStr[0]);
				sp2 = Long.parseLong(inStr[2]);
			}
			if (sp1 == sp2) {
				StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": 0\n");
				String builderStr = builder.toString();
				System.out.print(builderStr);
				writer.write(builderStr);
				continue;
			}
			double r1 = 0, r2 = 0;
			if (sp1 > sp2) {
				long temp = sp1;
				sp1 = sp2;
				sp2 = temp;
				int temp1 = deg1;
				deg1 = deg2;
				deg2 = temp1;
			}
			r1 = (double) 360 / sp1;
			r2 = (double) 360 / sp2;
			if (deg1 <= deg2) {
				// Case 1: slower guy in middle
				deg2 = 2 * 360 - deg2;
				deg1 = 360 - deg1;
			} else {
				// Case 2: faster guy in middle
				deg2 = 360 - deg2;
				deg1 = 360 - deg1;
			}
			if (deg1 * r1 < deg2 * r2) {
				StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": 0\n");
				String builderStr = builder.toString();
				System.out.print(builderStr);
				writer.write(builderStr);
			} else {
				StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": 1\n");
				String builderStr = builder.toString();
				System.out.print(builderStr);
				writer.write(builderStr);
			}
		}
		reader.close();
		writer.close();
	}
}
