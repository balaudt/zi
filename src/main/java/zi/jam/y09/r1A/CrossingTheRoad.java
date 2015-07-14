package zi.jam.y09.r1A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.commons.lang3.text.StrBuilder;

//TODO - Incomplete
public class CrossingTheRoad {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/2/B-small-practice-0.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/2/B-small-practice-0.out"));
		int c = Integer.parseInt(reader.readLine());
		for (int i = 0; i < c; i++) {
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			String[] inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int m = Integer.parseInt(inStr[1]);
			int[][] s = new int[n][m];
			int[][] w = new int[n][m];
			int[][] t = new int[n][m];
			for (int j = 0; j < n; j++) {
				inStr = reader.readLine().split(" ");
				for (int k = 0; k < m; k++) {
					s[j][k] = Integer.parseInt(inStr[k * 3]);
					w[j][k] = Integer.parseInt(inStr[k * 3 + 1]);
					t[j][k] = Integer.parseInt(inStr[k * 3 + 2]);
				}
			}
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}
}
