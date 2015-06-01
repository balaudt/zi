package zi.jam.y14.r1C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.text.StrBuilder;

public class Enclosure {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("C-small-attempt3.bin"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C-small-attempt3.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inps = reader.readLine().split(" ");
			int n = Integer.parseInt(inps[0]);
			int m = Integer.parseInt(inps[1]);
			int k = Integer.parseInt(inps[2]);
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			if (k <= 4) {
				int ans = 1;
				switch (k) {
				case 4:
					if (n == 1 || m == 1) {
						ans = 3;
					} else if (n == 2 && m == 2) {
						ans = 2;
					} else if (n == 2 || m == 2) {
						ans = 3;
					} else {
						ans = 4;
					}
					break;
				case 3:
					if (n == 1 || m == 1) {
						ans = 2;
					} else if (n == 2 && m == 2) {
						ans = 2;
					} else {
						ans = 3;
					}
					break;
				case 2:
					if (n * m == 2) {
						ans = 1;
					} else {
						ans = 2;
					}
				}
				System.out.println(ans);
				writer.write(builder.append(ans).append('\n').toString());
				continue;
			}
			boolean found = false;
			for (int x1 = 2; x1 <= n + 2 && !found; x1++) {
				for (int y1 = 2; y1 <= m + 2; y1++) {
					int x = x1 - 2;
					int y = y1 - 2;
					if ((2 * (x + y) + x * y) >= k && (2 * (x + y) < k)) {
						System.out.println(2 * (x + y));
						writer.write(builder.append(2 * (x + y)).append('\n').toString());
						found = true;
						break;
					}
				}
			}
			if (!found) {
				if (2 * (n + m) - 4 >= k) {
					writer.write(builder.append(k).append('\n').toString());
				} else {
					writer.write(builder.append(2 * (n + m) - 4).append('\n').toString());
				}
			}
		}
		reader.close();
		writer.close();
	}

}
