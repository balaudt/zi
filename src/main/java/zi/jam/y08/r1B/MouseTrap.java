package zi.jam.y08.r1B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.commons.lang3.text.StrBuilder;

public class MouseTrap {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/2/C-small-practice-1.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/2/C-small-practice-1.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			System.out.println(i+1);
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			int k = Integer.parseInt(reader.readLine());
			int[] nos = new int[k];
			int l = 0;
			for (int j = 0; j < nos.length; j++) {
				int count = 0;
				while (true) {
					if (nos[l] == 0)
						count++;
					if (count == j + 1) {
						nos[l] = j + 1;
						break;
					}
					l++;
					if (l == nos.length)
						l = 0;
				}
			}
			// System.out.println(Arrays.toString(nos));
			String[] indStr = reader.readLine().split(" ");
			for (int j = 1; j < indStr.length; j++) {
				builder.append(nos[Integer.parseInt(indStr[j]) - 1]).append(' ');
			}
			builder.append('\n');
			// System.out.print(builder.toString());
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}
}
