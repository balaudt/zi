package zi.jam.y15.r1C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.text.StrBuilder;

public class C {

	public static final String FOLDER_ROOT = "C:/ft/24/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "C-sample.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "C-sample.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			Integer.parseInt(inStr[0]);
			int d = Integer.parseInt(inStr[1]);
			int v = Integer.parseInt(inStr[2]);

			String[] ds = reader.readLine().split(" ");
			Set<Integer> existing = new HashSet<Integer>();
			for (int j = 0; j < d; j++) {
				existing.add(Integer.parseInt(ds[j]));
			}

			for (int j = 1; j <= v; j++) {
			}

			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}
}
