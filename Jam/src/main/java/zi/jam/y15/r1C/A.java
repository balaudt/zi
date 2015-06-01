package zi.jam.y15.r1C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.commons.lang3.text.StrBuilder;

public class A {

	public static final String FOLDER_ROOT = "C:/ft/24/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "A-large.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "A-large.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			int r = Integer.parseInt(inStr[0]);
			int c = Integer.parseInt(inStr[1]);
			int w = Integer.parseInt(inStr[2]);
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			if (w == 1) {
				builder.append(r * c).append('\n');
				String builderStr = builder.toString();
				System.out.print(builderStr);
				writer.write(builderStr);
				continue;
			}
			int ans;
			if (c % w == 0)
				ans = (c / w + w - 1) * r;
			else
				ans = (c / w + w) * r;
			builder.append(ans).append('\n');
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}
}
